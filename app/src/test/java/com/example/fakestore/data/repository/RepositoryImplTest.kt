package com.example.fakestore.data.repository

import com.example.fakestore.data.model.products.ProductsItemModel
import com.example.fakestore.data.model.products.ProductsModel
import com.example.fakestore.data.remote.ApiRequest
import com.example.fakestore.ui.errorhandling.ErrorHandling.doIfSuccess
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RepositoryImplTest {

    @Mock
    lateinit var productsApi: ApiRequest
    @Before
    fun setUp()
    {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetProducts_EmptyList()= runTest {
        val products = ProductsModel()
        Mockito.`when`(productsApi.getProducts()).thenReturn(products)

        val repositoryTest= RepositoryImpl(productsApi)
        val result= repositoryTest.getProducts()
            assertEquals(0,result.size)
    }

    @Test
    fun testGetProducts_expectedProductList()= runTest {
        val products = listOf(
            ProductsItemModel(
                id = 1,
                title = "Widget",
                description = "A high-quality widget that does amazing things.",
                price = 19.99,
                image = "https://example.com/widget.jpg"
            ),
            ProductsItemModel(
                id = 2,
                title = "Gizmo",
                description = "A cutting-edge gizmo that will revolutionize your life.",
                price = 29.99,
                image = "https://example.com/gizmo.jpg"
            )
        )
        val products_sample = ProductsModel()
        products_sample.addAll(products)
        Mockito.`when`(productsApi.getProducts()).thenReturn(products_sample)

        val repositoryTest= RepositoryImpl(productsApi)
        val result= repositoryTest.getProducts()
        assertEquals(2,result.size)
    }
}