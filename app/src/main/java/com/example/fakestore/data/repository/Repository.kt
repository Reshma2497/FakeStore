package com.example.fakestore.data.repository

import com.example.fakestore.data.model.products.ProductsModel

interface Repository {
    suspend fun getProducts(): ProductsModel
}