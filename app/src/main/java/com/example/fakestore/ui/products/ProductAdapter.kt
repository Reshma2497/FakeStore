package com.example.fakestore.ui.products

import android.annotation.SuppressLint
import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakestore.R
import com.example.fakestore.data.model.products.ProductsItemModel
import com.example.fakestore.data.model.products.ProductsModel
import com.example.fakestore.databinding.ItemProductBinding
import com.example.fakestore.ui.helper.getCurrency
import kotlinx.coroutines.NonDisposableHandle.parent

class ProductAdapter (var products: ProductsModel): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    var onItemClick: ((ProductsItemModel)-> Unit)?= null

    class ViewHolder (val view: View): RecyclerView.ViewHolder(view) {
        val binding= ItemProductBinding.bind(view)
        @RequiresApi(Build.VERSION_CODES.N)
        fun handleData(item: ProductsItemModel?){
            item?.let {

                Glide.with(view).load(it.image).placeholder(R.drawable.ic_launcher_foreground)
                    .into(binding.ivImage)
                binding.tvTitle.text= item.title

                binding.tvPrice.text= getCurrency(item.price as Double)
                binding.ratingBar.rating=item.rating?.rate!!.toFloat()
                binding.tvRating.text=item.rating?.rate.toString()
            }
        }



    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent,false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.handleData(products?.get(position))
        holder.itemView.setOnClickListener{
            products?.get(position)?.let {
                onItemClick?.invoke(it)
            }
        }
    }

    override fun getItemCount():  Int= products.size?:0


    // update adapter with the filter data
    fun updateData(filteredProducts:ProductsModel) {
        products = filteredProducts
        notifyDataSetChanged()
    }

}