package mxrampage.fetchsample.rest.services

import mxrampage.fetchsample.rest.dao.ItemsDAO
import retrofit2.Response
import retrofit2.http.GET

interface ItemsService {
    @GET("hiring.json")
    suspend fun getItems(): Response<List<ItemsDAO>>
}
