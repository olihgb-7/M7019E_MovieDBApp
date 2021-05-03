package com.ltu.m7019e.m7019e_moviedbapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ltu.m7019e.m7019e_moviedbapp.adapter.MovieReviewsAdapter
import com.ltu.m7019e.m7019e_moviedbapp.adapter.MovieTrailersAdapter
import com.ltu.m7019e.m7019e_moviedbapp.databinding.FragmentMovieReviewsAndTrailersBinding
import com.ltu.m7019e.m7019e_moviedbapp.model.Movie
import com.ltu.m7019e.m7019e_moviedbapp.viewmodel.MovieReviewsAndTrailersViewModel
import com.ltu.m7019e.m7019e_moviedbapp.viewmodel.MovieReviewsAndTrailersViewModelFactory
import timber.log.Timber


class MovieReviewsAndTrailersFragment : Fragment() {

    private lateinit var viewModel: MovieReviewsAndTrailersViewModel
    private lateinit var viewModelFactory: MovieReviewsAndTrailersViewModelFactory

    private var _binding: FragmentMovieReviewsAndTrailersBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieReviewsAndTrailersBinding.inflate(inflater)
        movie = MovieReviewsAndTrailersFragmentArgs.fromBundle(requireArguments()).movie

        val application = requireNotNull(this.activity).application
        viewModelFactory = MovieReviewsAndTrailersViewModelFactory(movie.id, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieReviewsAndTrailersViewModel::class.java)

        // Setup for movie review list in the layout
        val movieReviewsAdapter = MovieReviewsAdapter()
        binding.movieReviewsRv.adapter = movieReviewsAdapter
        viewModel.reviewList.observe(viewLifecycleOwner, { reviewList ->
            reviewList?.let {
                movieReviewsAdapter.submitList(reviewList)
            }
        })

        // Setup for movie trailer list in the layout
        val movieTrailersAdapter = MovieTrailersAdapter()
        binding.movieTrailersRv.adapter = movieTrailersAdapter
        viewModel.trailerList.observe(viewLifecycleOwner, { trailerList ->
            trailerList?.let {
                movieTrailersAdapter.submitList(trailerList)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backToMovieDetail.setOnClickListener {
            findNavController().navigate(MovieReviewsAndTrailersFragmentDirections.actionMovieReviewsAndTrailersFragmentToMovieDetailFragment(movie))
        }
    }
}