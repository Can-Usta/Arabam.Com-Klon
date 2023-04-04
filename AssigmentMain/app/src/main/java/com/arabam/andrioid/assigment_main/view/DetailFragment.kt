package com.arabam.andrioid.assigment_main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.arabam.andrioid.assigment_main.R
import com.arabam.andrioid.assigment_main.adapter.CarRecyclerAdapter
import com.arabam.andrioid.assigment_main.databinding.FragmentDetailBinding
import com.arabam.andrioid.assigment_main.service.CarApi
import com.arabam.andrioid.assigment_main.util.downloadImageForApi
import com.arabam.andrioid.assigment_main.viewmodel.CarDetailViewModel
import com.bumptech.glide.Glide


class DetailFragment : Fragment() {
    private  var carId : Int = 0
    private var isImageFullScreen : Boolean = false
    private lateinit var binding : FragmentDetailBinding

    private lateinit var viewModel: CarDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =ViewModelProvider(this)[CarDetailViewModel::class.java]
        arguments?.let {
            carId = DetailFragmentArgs.fromBundle(it).carId
        }
        viewModel.getDetail(carId)
        observLiveData()
        binding.detailImageView.setOnClickListener(View.OnClickListener {
            if (!isImageFullScreen){
                isImageFullScreen = true
                binding.detailImageView.layoutParams.height= view.parent.layoutDirection
                binding.detailImageView.layoutParams.width = view.parent.layoutDirection
            }
            else{
                isImageFullScreen = false
                binding.detailImageView.layoutParams.height = view.height
                binding.detailImageView.layoutParams.width = view.width
            }
        })


    }
    fun observLiveData(){
        viewModel.carLiveData.observe(viewLifecycleOwner, Observer { car->
            car?.let {

                binding.detailDate.text = it.date
                binding.detailModelName.text = it.modelName
                binding.detailLocation.text = it.location.cityName+ ", " + it.location.townName
                binding.detailPrice.text = it.price.toString()
                binding.detailProperties.text = it.properties.toString()
                binding.detailTitle.text = it.title
                binding.detailUserInfo.text = it.userInfo.toString()


                binding.detailImageView.downloadImageForApi(it.photos?.firstOrNull(),
                    CircularProgressDrawable(requireContext()))
                binding.mainLinearLayout.visibility = View.VISIBLE
                binding.detailErrorTV.visibility = View.GONE
                println(it.photos)


            }
        })
        viewModel.detailErrorMessage.observe(viewLifecycleOwner, Observer {error->
            error?.let {
                if (error){
                    binding.mainLinearLayout.visibility = View.GONE
                    binding.detailErrorTV.visibility = View.VISIBLE
                }
            }
        })
    }
}