package com.ltu.m7019e.m7019e_moviedbapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ltu.m7019e.m7019e_moviedbapp.databinding.FragmentMovieReviewsBinding
import com.ltu.m7019e.m7019e_moviedbapp.model.Movie


class MovieReviewsFragment : Fragment() {

    private var _binding: FragmentMovieReviewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieReviewsBinding.inflate(inflater)

        movie = MovieReviewsFragmentArgs.fromBundle(requireArguments()).movie

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backToMovieDetail.setOnClickListener {
            findNavController().navigate(MovieReviewsFragmentDirections.actionMovieReviewsFragmentToMovieDetailFragment(movie))
        }
    }
}