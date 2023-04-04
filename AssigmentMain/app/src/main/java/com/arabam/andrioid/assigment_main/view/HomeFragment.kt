package com.arabam.andrioid.assigment_main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arabam.andrioid.assigment_main.R
import com.arabam.andrioid.assigment_main.adapter.CarRecyclerAdapter
import com.arabam.andrioid.assigment_main.databinding.FragmentHomeBinding
import com.arabam.andrioid.assigment_main.viewmodel.CarListViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel :CarListViewModel
    private val recyclerAdapter =CarRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CarListViewModel::class.java]

        viewModel.refreshCarList(20,0)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter =recyclerAdapter
        recyclerAdapter.onClickItem {
            val action =HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }

        binding.nextPageButton.setOnClickListener(){
            val nextPage = HomeFragmentDirections.actionHomeFragmentToSecondPageFragment()
            findNavController().navigate(nextPage)
        }
        //recycler item altına gölge ekleme
        binding.recyclerView.addItemDecoration(DividerItemDecoration(binding.recyclerView.context,DividerItemDecoration.VERTICAL))

        binding.swipeRefresh.setOnRefreshListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.errorTextView.visibility = View.GONE
            binding.recyclerView.visibility = View.GONE
            viewModel.refreshCarList(10,0)
            binding.swipeRefresh.isRefreshing = false
        }
        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.cars.observe(viewLifecycleOwner, Observer {cars->
            cars?.let {
                binding.recyclerView.visibility = View.VISIBLE
                binding.errorTextView.visibility = View.GONE
                recyclerAdapter.updateCarList(cars)
            }
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {error->
            error?.let {
                if (error==true){
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                else{
                    binding.errorTextView.visibility = View.GONE
                }
            }
        })
        viewModel.carLoad.observe(viewLifecycleOwner,Observer{load->
            load?.let {
                if (load){
                    binding.recyclerView.visibility = View.GONE
                    binding.errorTextView.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }else{
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }
}