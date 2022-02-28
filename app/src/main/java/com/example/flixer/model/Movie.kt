package com.example.flixer.model

import org.json.JSONArray

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String, val overview: String){
    val imageurl = "https://image.tmdb.org/t/p/w342/$poster_path"
    companion object{
        fun fromJsonArray(movieArray: JSONArray): List<Movie> {
            val movieList = mutableListOf<Movie>()
            for (i in 0 until movieArray.length()){
                val movieObject = movieArray.getJSONObject(i)
                val id = movieObject.getInt("id")
                val title = movieObject.getString("title")
                val posterpath = movieObject.getString("poster_path")
                val overview = movieObject.getString("overview")
                movieList.add(Movie(id, title, posterpath, overview))
            }
            return movieList
        }
    }
}