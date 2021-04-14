package com.ltu.m7019e.m7019e_moviedbapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("posterImageUrl")
fun bindPosterImage(imgView: ImageView, imgUrl: String) {
    imgUrl.let { posterPath ->
        Glide
                .with(imgView)
                .load(Constants.POSTER_IMAGE_BASE_URL + Constants.IMAGE_WIDTH + posterPath)
                .into(imgView);
    }
}