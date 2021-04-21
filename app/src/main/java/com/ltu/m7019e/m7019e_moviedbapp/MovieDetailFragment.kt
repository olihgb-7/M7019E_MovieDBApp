package com.ltu.m7019e.m7019e_moviedbapp

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


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie: Movie
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

        MoviesDetail().list.forEach { movieDetail ->
            if (movie.id == movieDetail.id) {
                movieDetailHomepage = movieDetail.homepage
                movieDetailImbdId = movieDetail.imdb_id
                binding.movieDetail = movieDetail
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backToMovieList.setOnClickListener {
            findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailFragmentToMovieListFragment())
        }

        binding.nextToThirdFragment.setOnClickListener {
            findNavController().navigate(
                MovieDetailFragmentDirections.actionMovieDetailFragmentToThirdFragment(
                    movie
                )
            )
        }

        binding.movieDetailHomepage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(movieDetailHomepage)
            startActivity(intent)
        }

        binding.movieDetailImdbId.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.imdb.com/title/$movieDetailImbdId")
            startActivity(intent)
        }
    }
}