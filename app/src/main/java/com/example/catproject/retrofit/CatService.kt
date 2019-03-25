package com.example.catproject.retrofit

import com.example.catproject.BREEDS_PATH
import com.example.catproject.IMAGES_SEARCH_PATH
import com.example.catproject.retrofit.model.Breed
import com.example.catproject.retrofit.model.BreedImage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {

    /*@Headers("x-api-key",CAT_API_KEY)*/
    @GET(BREEDS_PATH)
    fun loadBreeds(@Query("limit") limit:Int) : Call<List<Breed>>

    @GET(IMAGES_SEARCH_PATH)
    fun getBreedImage(@Query("breed_id") breedID:String): Call<List<BreedImage>>

}