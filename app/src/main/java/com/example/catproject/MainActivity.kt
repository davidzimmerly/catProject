package com.example.catproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.catproject.retrofit.CatService
import com.example.catproject.retrofit.RetrofitClientInstance
import com.example.catproject.retrofit.model.Breed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retroFitService = RetrofitClientInstance.getRetrofit()?.create(CatService::class.java)
        val call  = retroFitService?.loadBreeds(1)
        call?.enqueue(object : Callback<List<Breed>> {
            override fun onFailure(call: Call<List<Breed>>, t: Throwable) {
                Log.d(TAG, "onFailure ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<List<Breed>>, response: Response<List<Breed>>) {
                if (response.isSuccessful){
                    Log.d(TAG, "onResponse successful: ${response.body()}")
                }else{
                    Log.d(TAG, "onResponse unsuccessful: ${response.errorBody()}")
                }
            }
        })
    }
}
