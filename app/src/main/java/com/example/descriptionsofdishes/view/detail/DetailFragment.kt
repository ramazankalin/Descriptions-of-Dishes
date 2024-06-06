package com.example.descriptionsofdishes.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.descriptionsofdishes.R
import com.example.descriptionsofdishes.databinding.FragmentDetailBinding
import com.example.descriptionsofdishes.util.ApplicationViewModelFactory
import com.example.descriptionsofdishes.util.downloadURL
import com.example.descriptionsofdishes.viewmodel.MainViewModel
import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.InterstitialAd


class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding
    private val viewModel : MainViewModel by viewModels {
        ApplicationViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)
        viewModel.findByName(args.category.title)
        initUI()
        return binding.root
    }

    private fun initUI() {
        viewModel.category.observe(viewLifecycleOwner) {category ->
            with(binding) {
                tvCategoryName.text = category.title
                tvCategoryDescription.text = category.description
                ivCategory.downloadURL(category.imageUrl)
            }
        }
    }
}