package com.ltu.m7019e.m7019e_moviedbapp

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ltu.m7019e.m7019e_moviedbapp.database.MoviesDetail
import com.ltu.m7019e.m7019e_moviedbapp.databinding.FragmentMovieDetailBinding
import com.ltu.m7019e.m7019e_moviedbapp.model.Movie
import com.ltu.m7019e.m7019e_moviedbapp.model.MovieDetail
import timber.log.Timber


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie: Movie
    private lateinit var movieDetail: MovieDetail
    private lateinit var movieDetailHomepage: String
    private lateinit var movieDetailImbdId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailBinding.inflate(inflater)
        movie = MovieDetailFragmentArgs.fromBundle(requireArguments()).movie
        binding.movie = movie


        // Handling the Movie Details for LAB 2
        /*
        movieDetail = MoviesDetail().list.find {movieDetail -> movieDetail.id == movie.id }!! // Find the correct MovieDetail object from the MoviesDetail database
        movieDetailHomepage = movieDetail.homepage // Set the movie detail homepage
        movieDetailImbdId = movieDetail.imdb_id // Set the movie detail imdb_id
        binding.movieDetail = movieDetail // Bind the MovieDetail object to the view
         */

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Back button to go to movie list
        binding.backToMovieList.setOnClickListener {
            findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailFragmentToMovieListFragment())
        }

        // Next button to go to the MovieReviewFragment
        binding.nextToThirdFragment.setOnClickListener {
            // Send movie as argument to handle correct behaviour when moving back from MovieReviewFragment
            findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailFragmentToMovieReviewsFragment(movie))
        }

        // Click listener for the homepage text, start intent to go to browser
        binding.movieDetailHomepage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(movieDetailHomepage)
            startActivity(intent)
        }

        // Click listener for the imdb_id text, start intent to go to imdb app
        binding.movieDetailImdbId.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.imdb.com/title/$movieDetailImbdId")
            startActivity(intent)
        }
    }
}