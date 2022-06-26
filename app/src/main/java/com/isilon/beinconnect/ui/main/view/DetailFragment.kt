package com.isilon.beinconnect.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.*
import com.isilon.beinconnect.databinding.FragmentDetailBinding
import com.isilon.beinconnect.databinding.PlayerBinding


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var bindingMusic: PlayerBinding

    private var exoPlayer: ExoPlayer? = null
    private var playbackPosition = 0L
    private var playWhenReady = true
    private lateinit var styledPlayerView: StyledPlayerView
    companion object {
        const val URL =
            "https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentDetailBinding.inflate(inflater, container, false)

        bindingMusic = PlayerBinding.inflate(inflater, container, false)



        return binding.root
    }

    private fun preparePlayer() {
        try {
            exoPlayer = ExoPlayer.Builder(requireContext()).build()
            exoPlayer?.playWhenReady = true
            binding.playerView.player = exoPlayer
            val defaultHttpDataSourceFactory = DefaultHttpDataSource.Factory()
            val mediaItem =
                MediaItem.fromUri(URL)
            val mediaSource =
                HlsMediaSource.Factory(defaultHttpDataSourceFactory).createMediaSource(mediaItem)
            exoPlayer?.setMediaSource(mediaSource)
            exoPlayer?.seekTo(playbackPosition)
            exoPlayer?.playWhenReady = playWhenReady
            exoPlayer?.prepare()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    private fun releasePlayer() {
        exoPlayer?.let { player ->
            playbackPosition = player.currentPosition
            playWhenReady = player.playWhenReady
            player.release()
            exoPlayer = null
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adult = arguments?.getBoolean("adult")
        val imageUrl = arguments?.getString("avatar")
        val releaseDate = arguments?.getString("releaseDate")
        val title = arguments?.getString("title")
        val lang = arguments?.getString("originalLanguage")
        val overview = arguments?.getString("overview")

        if(adult == true){
            binding.ivAdult.visibility = View.VISIBLE
        }else
            binding.ivChild.visibility = View.VISIBLE
        //binding.tvAdult.text = adult.toString()
        binding.tvReleaseDate.text = releaseDate
        binding.tvMovieTitle.text = title
        binding.movieLanguage.text = lang
        binding.tvMovieOverview.text = overview

        Log.e("img","http://image.tmdb.org/t/p/w185"+imageUrl)
        Glide.with(binding.ivDetailAvatar.context)
            .load("http://image.tmdb.org/t/p/w185"+imageUrl)
            .into(binding.ivDetailAvatar)

        binding.btnFragman.setOnClickListener {
            preparePlayer()
            binding.lnDetailMain.visibility = View.GONE
            binding.lnBtnMain.visibility = View.GONE
            binding.playerView.visibility = View.VISIBLE


        }


        Log.e("Adult",adult.toString())
        Log.e("avatarImg",imageUrl.toString())
        Log.e("ReleaseDate",releaseDate.toString())
    }


}