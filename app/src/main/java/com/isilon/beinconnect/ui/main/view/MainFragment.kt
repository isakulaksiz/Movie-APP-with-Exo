package com.isilon.beinconnect.ui.main.view

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.isilon.beinconnect.R
import com.isilon.beinconnect.data.api.ApiHelper
import com.isilon.beinconnect.data.api.ApiServiceImpl
import com.isilon.beinconnect.data.model.Result
import com.isilon.beinconnect.databinding.FragmentMainBinding
import com.isilon.beinconnect.ui.base.ViewModelFactory
import com.isilon.beinconnect.ui.main.adapter.MainAdapter
import com.isilon.beinconnect.ui.main.adapter.ViewPagerAdapter
import com.isilon.beinconnect.ui.main.viewmodel.BeinConnectViewModel
import com.isilon.beinconnect.utils.Constants
import com.isilon.beinconnect.utils.Status
import kotlin.system.exitProcess


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var mainViewModel: BeinConnectViewModel
    private lateinit var adapter: MainAdapter

    private lateinit var mViewPagerAdapter: ViewPagerAdapter
    private var dotscount=0
    private lateinit var dots: Array<ImageView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        try {
            Handler().postDelayed({
                showViewPagerItems(MainAdapter.mainMovieImg)
            },1500)
        }catch (e: Exception){
            e.printStackTrace()
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()

        setUpViewModel()
        setUpObserver()
        mainViewModel.fetchMovies(Constants.API_KEY, Constants.FIRST_PAGE)
    }


    private fun setUpObserver() {
        mainViewModel.data.observe(viewLifecycleOwner, Observer {
            when (it.status){
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { data -> renderList(data.results) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING ->{
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun setUpViewModel() {
        mainViewModel = ViewModelProviders.of(this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(BeinConnectViewModel::class.java)
    }

    private fun setUpUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            //LinearLayoutManager(requireContext())
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation)
        )



        binding.recyclerView.adapter = adapter

    }

    private fun renderList(data: List<Result>) {
        adapter.addData(data)
        adapter.notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
     fun showViewPagerItems(images: ArrayList<String>){

        mViewPagerAdapter = ViewPagerAdapter(requireContext(), images)
        binding.viewpager.adapter = mViewPagerAdapter

        dotscount = mViewPagerAdapter.count
        dots = arrayOfNulls(dotscount)


        binding.lnViewpager.removeAllViews()




        for (i in 0 until dotscount) {
            dots[i] = ImageView(requireContext())
            dots[i]!!.setImageDrawable(
                getDrawable(
                    requireContext(),
                    R.drawable.non_activedots
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            params.setMargins(8, 0, 8, 0)
            binding.lnViewpager.addView(dots[i], params)
        }

        try {
            val urlText: String = images[0].toString()

            Glide.with(requireContext())
                .asBitmap()
                .load(urlText)

                .into(object : SimpleTarget<Bitmap?>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap?>?
                    ) {

                    }
                })
        }catch (e: Exception){
            e.printStackTrace()
        }


        dots[0]!!.setImageDrawable(getDrawable(requireContext(), R.drawable.active_dots))

        binding.viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {


            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                var i = 0
                while (i < dotscount) {
                    dots[i]!!.setImageDrawable(
                        getDrawable(
                            requireContext(),
                            R.drawable.non_activedots
                        )
                    )
                    val urlText: String = images[i].toString()

                    Glide.with(requireContext())
                        .asBitmap()
                        .load(urlText)
                        .into(object : SimpleTarget<Bitmap?>() {
                            override fun onResourceReady(
                                resource: Bitmap,
                                transition: Transition<in Bitmap?>?
                            ) {

                            }
                        })
                    i++
                }
                dots[position]!!.setImageDrawable(
                    getDrawable(
                        requireContext(),
                        R.drawable.active_dots
                    )
                )
            }

            override  fun onPageScrollStateChanged(state: Int) {

            }
        })
    }
}