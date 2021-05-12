package com.ltu.m7019e.m7019e_moviedbapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ltu.m7019e.m7019e_moviedbapp.databinding.MovieTrailerItemBinding
import com.ltu.m7019e.m7019e_moviedbapp.model.MovieTrailer

class MovieTrailersAdapter(private val trailerClickListener: MovieTrailerClickListener) : ListAdapter<MovieTrailer, MovieTrailersAdapter.ViewHolder>(
    MoviewTrailerDiffCallback()
) {

    class ViewHolder(private var binding: MovieTrailerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movieTrailer: MovieTrailer, trailerClickListener: MovieTrailerClickListener) {
            binding.movieTrailer = movieTrailer
            binding.clickListener = trailerClickListener

            if (binding.movieTrailer!!.site == "YouTube") {
                binding.movieTrailerWv.settings.javaScriptEnabled = true
                binding.movieTrailerWv.settings.pluginState = WebSettings.PluginState.ON
                binding.movieTrailerWv.loadUrl("https://www.youtube.com/embed/" + binding.movieTrailer!!.key)
                binding.movieTrailerWv.webChromeClient = WebChromeClient()
            }
            else {
                binding.movieTrailerLink.visibility = View.GONE
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieTrailerItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(getItem(position), trailerClickListener)
    }
}

class MoviewTrailerDiffCallback : DiffUtil.ItemCallback<MovieTrailer>() {
    override fun areItemsTheSame(oldItem: MovieTrailer, newItem: MovieTrailer): Boolean {
        return oldItem.trailer_id == newItem.trailer_id
    }

    override fun areContentsTheSame(oldItem: MovieTrailer, newItem: MovieTrailer): Boolean {
        return oldItem == newItem
    }
}

class MovieTrailerClickListener(val clickListener: (movieTrailer: MovieTrailer) -> Unit) {
    fun onClick(movieTrailer: MovieTrailer) = clickListener(movieTrailer)
}