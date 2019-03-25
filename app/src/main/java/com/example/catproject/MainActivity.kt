package com.example.catproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catproject.adapter.BreedAdapter
import com.example.catproject.retrofit.CatService
import com.example.catproject.retrofit.RetrofitClientInstance
import com.example.catproject.retrofit.model.Breed
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerAdapter: BreedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerAdapter = BreedAdapter(this, ArrayList())
        (myRecyclerView as RecyclerView).layoutManager= LinearLayoutManager(this)
        (myRecyclerView as RecyclerView).itemAnimator= DefaultItemAnimator()
        (myRecyclerView as RecyclerView).adapter = null
        (myRecyclerView as RecyclerView).adapter = recyclerAdapter


        val retroFitService = RetrofitClientInstance.getRetrofit()?.create(CatService::class.java)
        val call  = retroFitService?.loadBreeds(10)
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
}
