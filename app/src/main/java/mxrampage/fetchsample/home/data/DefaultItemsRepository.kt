package mxrampage.fetchsample.home.data

import mxrampage.fetchsample.models.ItemsModel
import mxrampage.fetchsample.rest.dao.ItemsDAO
import mxrampage.fetchsample.rest.services.ItemsService
import mxrampage.fetchsample.utils.Resource
import java.util.SortedMap
import javax.inject.Inject

class DefaultItemsRepository @Inject constructor(
    private val itemsService: ItemsService
) : ItemsRepository {
    override suspend fun getItemsList(): Resource<ArrayList<ItemsModel>> {
        return try {
            val response = itemsService.getItems()
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    val itemsArray = body.toTypedArray()
                    val filteredNames = itemsArray.filter { it.name.isNullOrEmpty().not() }
                    filteredNames.sortedBy { it.listId }
                    val mappedItems = filteredNames.groupBy({ it.listId }, { it }).toSortedMap()
                    return@let Resource.success(createArrayOfItems(mappedItems))
                } ?: Resource.error(response.message(), null)
            } else {
                Resource.error(response.message(), null)
            }
        } catch (exception: Exception) {
            Resource.error("Data error", null)
        }
    }

    private fun createArrayOfItems(mappedItems: SortedMap<Int, List<ItemsDAO>>): ArrayList<ItemsModel> {
        val itemsModelArray = ArrayList<ItemsModel>()
        mappedItems.forEach { entry ->
            itemsModelArray.add(ItemsModel(entry.key, null, null))
            val sortedByName = entry.value.sortedWith { o1, o2 -> extractInt(o1) - extractInt(o2) }
            sortedByName.forEach { item ->
                itemsModelArray.add(ItemsModel(null, item.id, item.name.toString()))
            }
        }
        return itemsModelArray
    }

    private fun extractInt(item: ItemsDAO): Int {
        val num = item.name?.replace("\\D".toRegex(), "")
        return if (num.isNullOrEmpty()) 0 else Integer.parseInt(num)
    }
}
