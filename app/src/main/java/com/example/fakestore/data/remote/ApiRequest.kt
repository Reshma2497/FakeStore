package com.example.fakestore.data.remote

import com.example.fakestore.data.model.products.ProductsModel
import retrofit2.http.GET

interface ApiRequest {
    @GET(ApiDetails.PRODUCTS)
    suspend fun getProducts(): ProductsModel
}