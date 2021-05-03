package com.ltu.m7019e.m7019e_moviedbapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ltu.m7019e.m7019e_moviedbapp.databinding.FragmentMovieDetailBinding
import com.ltu.m7019e.m7019e_moviedbapp.model.Movie
import com.ltu.m7019e.m7019e_moviedbapp.utils.Constants
import com.ltu.m7019e.m7019e_moviedbapp.viewmodel.MovieDetailViewModel
import com.ltu.m7019e.m7019e_moviedbapp.viewmodel.MovieDetailViewModelFactory


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var viewModelFactory: MovieDetailViewModelFactory

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie: Movie
    private lateinit var movieGenres: MutableList<String>
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

        val application = requireNotNull(this.activity).application
        viewModelFactory = MovieDetailViewModelFactory(movie.id, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)

        movieGenres = mutableListOf<String>()
        viewModel.movieGenres.observe(viewLifecycleOwner, { genreList ->
            genreList.forEach { genre ->
                movieGenres.add(genre.name)
            }
            binding.movieDetailGenres.text = "Genres: $movieGenres"
        })

        viewModel.movieHomepage.observe(viewLifecycleOwner, { homepage ->
            movieDetailHomepage = homepage
            binding.movieDetailHomepage.text = "Homepage: $homepage"
        })

        viewModel.movieImdbId.observe(viewLifecycleOwner, { imdb_id ->
            movieDetailImbdId = imdb_id
            binding.movieDetailImdbId.text = "IMDB Page: " + Constants.IMDB_BASE_URL + imdb_id
        })

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
            findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailFragmentToMovieReviewsAndTrailersFragment(movie))
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
            intent.data = Uri.parse(Constants.IMDB_BASE_URL + movieDetailImbdId)
            startActivity(intent)
        }
    }
}