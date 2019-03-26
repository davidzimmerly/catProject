package com.example.catproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catproject.adapter.BreedAdapter
import com.example.catproject.retrofit.CatService
import com.example.catproject.retrofit.RetrofitClientInstance
import com.example.catproject.retrofit.model.Breed
import com.example.catproject.retrofit.model.BreedImage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.breed_item_view.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    var retroFitService :CatService? = null
    private lateinit var recyclerAdapter: BreedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerAdapter = BreedAdapter(this, ArrayList())
        (myRecyclerView as RecyclerView).layoutManager= LinearLayoutManager(this)
        (myRecyclerView as RecyclerView).itemAnimator= DefaultItemAnimator()
        (myRecyclerView as RecyclerView).adapter = null
        (myRecyclerView as RecyclerView).adapter = recyclerAdapter


        retroFitService = RetrofitClientInstance.getRetrofit()?.create(CatService::class.java)
        val call  = retroFitService?.loadBreeds(100)
        call?.enqueue(object : Callback<List<Breed>> {
            override fun onFailure(call: Call<List<Breed>>, t: Throwable) {
                Log.d(TAG, "onFailure ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<List<Breed>>, response: Response<List<Breed>>) {
                if (response.isSuccessful){
                    Log.d(TAG, "onResponse successful: ${response.body()}")
                    ((myRecyclerView.adapter as BreedAdapter).setData(response.body() as List<Breed>))
                }else{
                    Log.d(TAG, "onResponse unsuccessful: ${response.errorBody()}")
                }
            }
        })


    }

    fun breedClick(view: View){
        val thisBreed = view.tvBreedName.text.toString()
//        val thisDesc = view.tvDesc.text.toString()
//        this.toast(thisDesc)
        val call  = retroFitService?.getBreedImage(thisBreed)
        call?.enqueue(object : Callback<List<BreedImage>> {
            override fun onFailure(call: Call<List<BreedImage>>, t: Throwable) {
                Log.d(TAG, "onFailure ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<List<BreedImage>>, response: Response<List<BreedImage>>) {
                if (response.isSuccessful){
                    Log.d(TAG, "onResponse successful: ${response.body()}")
                    val result : List<BreedImage> = response.body() as List<BreedImage>
                    cardMainLayout.visibility = View.VISIBLE
                    imageView.load(result[0].url)
                    textView.text = view.tvDesc.text
                }else{
                    Log.d(TAG, "onResponse unsuccessful: ${response.errorBody()}")
                }
            }
        })
    }
    fun ImageView.load(url:String ){
        Glide.with(context).load(url).into(this)
    }
}
