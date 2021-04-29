package com.ltu.m7019e.m7019e_moviedbapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ltu.m7019e.m7019e_moviedbapp.database.MovieDatabase
import com.ltu.m7019e.m7019e_moviedbapp.database.MovieDatabaseDao
import com.ltu.m7019e.m7019e_moviedbapp.database.Movies
import com.ltu.m7019e.m7019e_moviedbapp.databinding.FragmentMovieListBinding
import com.ltu.m7019e.m7019e_moviedbapp.databinding.MovieListItemBinding
import com.ltu.m7019e.m7019e_moviedbapp.viewmodel.MovieListViewModel
import com.ltu.m7019e.m7019e_moviedbapp.viewmodel.MovieListViewModelFactory
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieListFragment : Fragment() {

    private lateinit var viewModel: MovieListViewModel
    private lateinit var viewModelFactory: MovieListViewModelFactory

    private lateinit var movieDatabaseDao: MovieDatabaseDao

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieListBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        movieDatabaseDao = MovieDatabase.getInstance(application).movieDatabaseDao

        viewModelFactory = MovieListViewModelFactory(movieDatabaseDao, application)
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

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_load_popular_movies -> {
                true
            }
            R.id.action_load_top_rated_movies -> {
                viewModel.addMovie()
                Timber.i("Adding a dummy Movie")
                true
            }
            R.id.action_load_saved_movies -> {
                viewModel.getSavedMovies()
                Timber.i("Getting saved movie details")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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