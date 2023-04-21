package com.example.fakestore.ui.products.details


import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.fakestore.R
import com.example.fakestore.data.model.products.ProductsItemModel
import com.example.fakestore.data.model.products.ProductsModel
import com.example.fakestore.databinding.FragmentProductDetailsBinding
import com.example.fakestore.ui.helper.getCurrency

class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var product: ProductsItemModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        product=arguments?.getSerializable("Product") as ProductsItemModel

        setupUI(product)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun setupUI(productDetails: ProductsItemModel?) {
        val view=binding.root
        view?.let {
            Glide.with(it).load(productDetails?.image).placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivImage)
        }
        binding.tvTitle.text=productDetails?.title
        //binding.tvRating.text=productDetails?.rating?.rate.toString()
        binding.ratingBar.rating=productDetails?.rating?.rate!!.toFloat()
        binding.tvPrice.text= getCurrency(productDetails?.price as Double)
        binding.tvCategory.text=productDetails?.category
        binding.tvDescription.text=productDetails?.description

    }

}