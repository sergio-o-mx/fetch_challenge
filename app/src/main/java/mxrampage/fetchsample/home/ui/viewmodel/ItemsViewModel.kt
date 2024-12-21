package mxrampage.fetchsample.home.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mxrampage.fetchsample.home.data.ItemsRepository
import mxrampage.fetchsample.models.ItemsModel
import mxrampage.fetchsample.utils.Resource
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val repository: ItemsRepository
) : ViewModel() {
    private val _itemsData = MutableLiveData<Resource<ArrayList<ItemsModel>>>()
    val itemsData: LiveData<Resource<ArrayList<ItemsModel>>> = _itemsData

    fun getItemsList() {
        _itemsData.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getItemsList()
            _itemsData.value = response
        }
    }
}
