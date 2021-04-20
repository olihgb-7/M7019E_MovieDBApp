package com.ltu.m7019e.m7019e_moviedbapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ltu.m7019e.m7019e_moviedbapp.database.Movies
import com.ltu.m7019e.m7019e_moviedbapp.databinding.FragmentMovieListBinding
import com.ltu.m7019e.m7019e_moviedbapp.databinding.MovieListItemBinding
import com.ltu.m7019e.m7019e_moviedbapp.viewmodel.MovieListViewModel
import com.ltu.m7019e.m7019e_moviedbapp.viewmodel.MovieListViewModelFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieListFragment : Fragment() {

    private lateinit var viewModel: MovieListViewModel
    private lateinit var viewModelFactory: MovieListViewModelFactory

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieListBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        viewModelFactory = MovieListViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)

        viewModel.movieList.observe(
            viewLifecycleOwner, { movieList ->
                movieList.forEach { movie ->
                    val movieListItemBinding: MovieListItemBinding = DataBindingUtil.inflate(inflater, R.layout.movie_list_item, container, false)
                    movieListItemBinding.movie = movie
                    movieListItemBinding.root.setOnClickListener {
                        this.findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFagment(movie))
                    }
                    binding.movieListLl.addView(movieListItemBinding.root)
                }
            }
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        val movies = Movies()
//
//        val movieList = view.findViewById<LinearLayout>(R.id.movie_list_ll)
//        val movieItem = movieList.findViewById<View>(R.id.movie_1)
//        val movieTitle = movieItem.findViewById<TextView>(R.id.movie_title)
//        val moviePoster = movieItem.findViewById<ImageView>(R.id.movie_poster)
//
//        movieTitle.text = movies.list[0].title
//
//        Glide
//                .with(this)
//                .load(Constants.POSTER_IMAGE_BASE_URL + Constants.IMAGE_WIDTH + movies.list[0].posterPath)
//                .into(moviePoster);
    }
}