package com.example.fakestore.data.repository

import com.example.fakestore.data.model.products.ProductsModel
import com.example.fakestore.data.remote.ApiRequest
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiRequest: ApiRequest
):Repository{
   override suspend fun getProducts() : ProductsModel=apiRequest.getProducts()
}