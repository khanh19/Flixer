package com.example.flixer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixer.model.Movie

class MovieAdapter(private val context: Context, private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){
    inner class ViewHolder(itemview: View) :  RecyclerView.ViewHolder(itemview){
        private val image = itemview.findViewById<ImageView>(R.id.image)
        private val title = itemview.findViewById<TextView>(R.id.Text1)
        private val overview  = itemview.findViewById<TextView>(R.id.textView2)

        fun bind(movie: Movie) {
            title.text = movie.title
            overview.text = movie.overview
            Glide.with(context).load(movie.imageurl).into(image)
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}
