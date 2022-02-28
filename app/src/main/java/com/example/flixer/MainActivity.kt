package com.example.flixer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixer.model.Movie
import okhttp3.Headers
import org.json.JSONException

private const val TAG ="MainActivity"
private const val MOVIE_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=ced9d61b7ec5379bb8581a9a6c63bfbd"
class MainActivity : AppCompatActivity() {
    private  val movielist = mutableListOf<Movie>()
    private lateinit var rv : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv = findViewById(R.id.recycler)
        val movieAdapter = MovieAdapter(this, movielist)
        rv.adapter = movieAdapter
        rv.layoutManager = LinearLayoutManager(this)
        val client = AsyncHttpClient()
        client.get(MOVIE_URL, object: JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
               try {
                   Log.i(TAG, "onSuccess: JSON data $json")
                   val movieArray = json!!.jsonObject.getJSONArray("results")
                   movielist.addAll(Movie.fromJsonArray(movieArray))
                   movieAdapter.notifyDataSetChanged()
                   Log.i(TAG, "Movie List $movielist")
                }
               catch (e:JSONException){
                   Log.e(TAG, "Error $e")

               }
            }
        })
    }
}