package com.isilon.beinconnect.ui.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.isilon.beinconnect.R
import com.isilon.beinconnect.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adult = arguments?.getBoolean("adult")
        val imageUrl = arguments?.getString("avatar")
        val releaseDate = arguments?.getString("releaseDate")

        binding.tvAdult.text = adult.toString()
        binding.tvReleaseDate.text = releaseDate

        Glide.with(binding.ivDetailAvatar.context)
            .load(imageUrl)
            .into(binding.ivDetailAvatar)
        Log.e("Adult",adult.toString())
        Log.e("avatarImg",imageUrl.toString())
        Log.e("ReleaseDate",releaseDate.toString())
    }


}