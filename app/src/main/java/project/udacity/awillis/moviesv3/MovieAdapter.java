package project.udacity.awillis.moviesv3;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<MoviePOJO> mMoviePOJO;
    private MovieAdapterOnClickHandler movieAdapterOnClickHandler;

    MovieAdapter(MovieAdapterOnClickHandler clickHandler, ArrayList<MoviePOJO> movies ) {
        this.mMoviePOJO = movies;
        this.movieAdapterOnClickHandler = clickHandler;
    }

    public interface MovieAdapterOnClickHandler {
        void onClick(View view, String movieClicked, int position );
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = android.R.layout.activity_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        String moviePosterPath = mMoviePOJO.get(position).getPoster();
        String movieTitle = mMoviePOJO.get(position).getTitle();

        String url = NetworkUtil.buildURLPoster(moviePosterPath);
        Picasso.get().load(url).placeholder(android.R.drawable.picture_frame).into(holder.mMovieImageView);
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mMovieImageView;
        TextView mMovieTitle;

        MovieViewHolder(View viewItem) {
            super(viewItem);
            mMovieImageView = viewItem.findViewById(R.id.iv_movie_poster);
            mMovieTitle = viewItem.findViewById(R.id.tv_movie_title);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            String movieTitle = mMoviePOJO.get(position).getOriginal_title();
            movieAdapterOnClickHandler.onClick(v, movieTitle,position);

        }
    }


    @Override
    public int getItemCount() {
        if(mMoviePOJO == null) return 0;
        return mMoviePOJO.size();
    }

    public void setMoviePOJO(ArrayList<MoviePOJO> moviePOJO) {
        mMoviePOJO = moviePOJO;
        notifyDataSetChanged();


    }




}
