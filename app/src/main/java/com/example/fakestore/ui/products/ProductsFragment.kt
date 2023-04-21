package com.example.fakestore.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fakestore.R
import com.example.fakestore.data.model.products.ProductsItemModel
import com.example.fakestore.data.model.products.ProductsModel
import com.example.fakestore.databinding.FragmentProductsBinding
import com.example.fakestore.ui.errorhandling.ErrorHandling.doIfFailure
import com.example.fakestore.ui.errorhandling.ErrorHandling.doIfSuccess
import com.example.fakestore.ui.helper.PopUp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    // creating a viewmodel instanve
    private val viewModel by viewModels<ProductsViewModel>()
    //helper class to show popup
    private val popUp= PopUp(this)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)

        viewModel.products.observe(viewLifecycleOwner, Observer { result->
            result.doIfSuccess{items->
                SetupUI(items)
            }
            result.doIfFailure{ message, throwable ->
                popUp.showErrorPopup(message?: "Unknown error")
            }
        })

        viewModel.getProducts()
        return binding.root
    }

    fun SetupUI(products : ProductsModel){
        val productAdapter= ProductAdapter(products)
        binding.rvProducts.apply {
            layoutManager= LinearLayoutManager(context)
            adapter= productAdapter
        }
        productAdapter.onItemClick = {
            val bundle = Bundle().apply {
                putSerializable("Product",it)
            }
            findNavController().navigate(
                R.id.action_navigation_products_to_productDetailsFragment,bundle
            )
        }
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                //TODO("Not yet implemented")
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                // to filter the data start with title given in a search bar
                val filteredList  = products.filter {
                    it.title?.contains(newText, ignoreCase = true)?:false
                }
                val filteredProducts = ProductsModel()
                filteredProducts.addAll(filteredList)

                // filteredPeople.setPeople
                productAdapter.updateData(filteredProducts)
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}