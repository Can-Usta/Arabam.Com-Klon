package com.arabam.andrioid.assigment_main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.os.bundleOf
import androidx.core.view.isEmpty
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.arabam.andrioid.assigment_main.R
import com.arabam.andrioid.assigment_main.databinding.CarRowBinding
import com.arabam.andrioid.assigment_main.databinding.FragmentHomeBinding
import com.arabam.andrioid.assigment_main.model.Car
import com.arabam.andrioid.assigment_main.util.createPlaceholder
import com.arabam.andrioid.assigment_main.util.downloadImageForApi
import com.arabam.andrioid.assigment_main.view.HomeFragment
import com.arabam.andrioid.assigment_main.view.HomeFragmentDirections
import java.util.*
import kotlin.collections.ArrayList


class CarRecyclerAdapter(val carList : ArrayList<Car>) : RecyclerView.Adapter<CarRecyclerAdapter.CarVH>(), Filterable {
    private lateinit var carItemClick: (Int) -> Unit
    private lateinit var binding2: FragmentHomeBinding



    fun onClickItem(
        carItemClick: (Int) -> Unit
    ) {
        this.carItemClick = carItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarVH {


        val binding =DataBindingUtil.inflate<CarRowBinding>(LayoutInflater.from(parent.context), R.layout.car_row,parent,false)
        return CarVH(binding)

    }

    override fun getItemCount(): Int {
        return carList.size
    }


    override fun onBindViewHolder(holder: CarVH, position: Int) {

        holder.bind(carList.get(position),carItemClick)

        holder.binding.rowImage.downloadImageForApi(carList.get(position).photo, createPlaceholder(holder.binding.root.context))
    }
    fun updateCarList(newCarList: List<Car>){
        carList.clear()
        carList.addAll(newCarList)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val searchText = p0.toString()
                carList.clear()
                if (binding2.svSearch.isEmpty()){
                    carList.addAll(carList)
                }
                else{
                    for (number in carList){
                        if (number.toString().toUpperCase(Locale.ROOT).contains(searchText.toUpperCase(Locale.ROOT))){
                            carList.add(number)
                        }
                    }
                }
                val filterResult = FilterResults()
                filterResult.values = carList
                return filterResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                notifyDataSetChanged()
            }

        }
    }


    class CarVH(val binding : CarRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Car, carItemClick: (Int) -> Unit){
            binding.root.setOnClickListener {
                item.id.let { it1 -> carItemClick.invoke(it1) }
            }
            binding.carTitle.text= item.title
            binding.carLocation.text =item.location?.cityName
            binding.carPrice.text = item.price.toString()


        }
    }
}