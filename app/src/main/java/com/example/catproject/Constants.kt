package com.example.catproject

import android.widget.ImageView
import com.bumptech.glide.Glide

const val CAT_API_KEY = "a5f6c6b1-6552-460e-b071-6a5224c23cba"
const val CATS_BASE_URL = "https://api.thecatapi.com/v1/"
const val BREEDS_PATH = "breeds/"
const val IMAGES_SEARCH_PATH = "images/search"

fun ImageView.load(url:String ){
    Glide.with(context).load(url).into(this)
}