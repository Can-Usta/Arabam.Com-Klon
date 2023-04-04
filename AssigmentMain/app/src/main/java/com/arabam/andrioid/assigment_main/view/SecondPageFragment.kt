package com.arabam.andrioid.assigment_main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arabam.andrioid.assigment_main.R
import com.arabam.andrioid.assigment_main.adapter.CarRecyclerAdapter
import com.arabam.andrioid.assigment_main.databinding.FragmentSecondPageBinding
import com.arabam.andrioid.assigment_main.viewmodel.CarListViewModel


class SecondPageFragment : Fragment() {
    private lateinit var binding : FragmentSecondPageBinding
    private lateinit var viewModel : CarListViewModel
    private val recyclerAdapter = CarRecyclerAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_second_page,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CarListViewModel::class.java]
        viewModel.refreshCarList(20,20)

        binding.secondPageRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.secondPageRecyclerView.adapter =recyclerAdapter

        recyclerAdapter.onClickItem {
            val action =SecondPageFragmentDirections.actionSecondPageFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }

        binding.secondPageRecyclerView.addItemDecoration(DividerItemDecoration(binding.secondPageRecyclerView.context,DividerItemDecoration.VERTICAL))

        binding.swipeRefresh.setOnRefreshListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.errorTextView.visibility = View.GONE
            binding.secondPageRecyclerView.visibility = View.GONE
            viewModel.refreshCarList(10,10)
            binding.swipeRefresh.isRefreshing = false
        }
        observeLiveData()

    }
    private fun observeLiveData(){
        viewModel.cars.observe(viewLifecycleOwner, Observer {cars->
            cars?.let {
                binding.secondPageRecyclerView.visibility = View.VISIBLE
                binding.errorTextView.visibility = View.GONE
                recyclerAdapter.updateCarList(cars)
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {error->
            error?.let {
                if (error==true){
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.secondPageRecyclerView.visibility = View.GONE
                }
                else{
                    binding.errorTextView.visibility = View.GONE
                }
            }
        })

        viewModel.carLoad.observe(viewLifecycleOwner,Observer{load->
            load?.let {
                if (load){
                    binding.secondPageRecyclerView.visibility = View.GONE
                    binding.errorTextView.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }else{
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }


}