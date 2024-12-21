package mxrampage.fetchsample.home.data

import mxrampage.fetchsample.models.ItemsModel
import mxrampage.fetchsample.utils.Resource

interface ItemsRepository {
    suspend fun getItemsList(): Resource<ArrayList<ItemsModel>>
}
