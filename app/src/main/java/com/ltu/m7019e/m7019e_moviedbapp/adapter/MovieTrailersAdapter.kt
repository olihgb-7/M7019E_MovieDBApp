package com.ltu.m7019e.m7019e_moviedbapp.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ltu.m7019e.m7019e_moviedbapp.databinding.MovieTrailerItemBinding
import com.ltu.m7019e.m7019e_moviedbapp.model.Movie
import com.ltu.m7019e.m7019e_moviedbapp.model.MovieTrailer
import com.ltu.m7019e.m7019e_moviedbapp.utils.Constants
import timber.log.Timber


class MovieTrailersAdapter(private val trailerClickListener: MovieTrailerClickListener) : ListAdapter<MovieTrailer, MovieTrailersAdapter.ViewHolder>(MoviewTrailerDiffCallback()) {

    class ViewHolder(private var binding: MovieTrailerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movieTrailer: MovieTrailer, trailerClickListener: MovieTrailerClickListener) {
            binding.movieTrailer = movieTrailer
            binding.clickListener = trailerClickListener
            binding.executePendingBindings()

            if (binding.movieTrailerLink.text == "YouTube") {
                binding.movieTrailerVv.visibility = View.GONE
                binding.movieTrailerLink.visibility = View.VISIBLE
            }
            else {
                binding.movieTrailerVv.visibility = View.VISIBLE
                binding.movieTrailerLink.visibility = View.GONE
                //binding.movieTrailerVv.setVideoPath()
                //binding.movieTrailerVv.start()
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