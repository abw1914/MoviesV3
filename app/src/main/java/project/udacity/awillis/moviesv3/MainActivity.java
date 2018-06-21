package project.udacity.awillis.moviesv3;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    public MovieAdapter movieAdapter;
    public RecyclerView movieRecyclerView;
    public ArrayList<MoviePOJO> moviePOJOS = null;
    private URL url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        movieRecyclerView = findViewById(R.id.rv_movies_grid);

        int value = this.getResources().getConfiguration().orientation;

        GridLayoutManager gridLayoutManager = null;
        if (value == Configuration.ORIENTATION_PORTRAIT) {
            gridLayoutManager = new GridLayoutManager(this, 2);
        } else {
            gridLayoutManager = new GridLayoutManager(this, 4);
        }

        movieRecyclerView.setLayoutManager(gridLayoutManager);
        movieRecyclerView.setHasFixedSize(true);
        movieAdapter = new MovieAdapter((MovieAdapter.MovieAdapterOnClickHandler) this, moviePOJOS);
        movieRecyclerView.setAdapter(movieAdapter);

        if (savedInstanceState == null || !savedInstanceState.containsKey(getString(R.string.saved_instance_movies))) {
            loadMovieData(R.id.menu_search_option);
        } else {
            moviePOJOS = savedInstanceState.getParcelableArrayList(getString(R.string.saved_instance_movies));
            if (moviePOJOS != null) {
                movieAdapter.setMoviePOJO(moviePOJOS);
            }


        }
    }

    public void showMovieData() {
        movieRecyclerView.setVisibility(View.VISIBLE);
    }

    public void loadMovieData(int param) {
        showMovieData();
        url = NetworkUtil.buildURL(param);
        new GetMovieAsync(this).execute(url);

    }

    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(getString(R.string.saved_instance_movies), moviePOJOS);
        super.onSaveInstanceState(outState);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu);
        return true;
    }

    public boolean onOptionItemSelected(MenuItem menuItem) {
        int itemThatWasClicked = menuItem.getItemId();
        url = null;

        switch (itemThatWasClicked) {
            case R.id.top_rated:
                loadMovieData(R.id.top_rated);
                return true;
            case R.id.popular:
                loadMovieData(R.id.popular);
                return true;
        }
        return onOptionItemSelected(menuItem);
    }

    public void onClick(View view, String movieClicked, int position) {
        Class destination = DetailActivity.class;
        Intent DetailedActivityIntent = new Intent(this, destination);
        DetailedActivityIntent.putExtra(getString(R.string.extra_movie), moviePOJOS.get(position));


    }

    private static class GetMovieAsync extends AsyncTask<URL, Void, ArrayList<MoviePOJO>> {

        private final WeakReference<MainActivity> activityWeakReference;

        GetMovieAsync(MainActivity context) {
            activityWeakReference = new WeakReference<>(context);

        }


        protected void onPreExecute() {
            super.onPreExecute();
            MainActivity mainActivity = activityWeakReference.get();
            if (mainActivity == null || mainActivity.isFinishing()) return;

            mainActivity.showMovieData();


        }


        @Override
        protected ArrayList<MoviePOJO> doInBackground(URL... urls) {
            MainActivity mainActivity = activityWeakReference.get();
            if (mainActivity == null || mainActivity.isFinishing())
                    return null;

            URL queryURL = urls[0];
            String movieQueryResults;
            try {
                movieQueryResults = NetworkUtil.buildURLPoster(queryURL);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return null;
        }

        public WeakReference<MainActivity> getActivityWeakReference() {
            return activityWeakReference;
        ParseMovieData.getMovieData(movieQueryResults);
        return mainActivity.MoviePOJO;
    }

    @Override
    protected void onPostExecute(ArrayList<MoviePOJO> movies) {
        // get a reference to the activity if it is still there
        MainActivity mainActivity = activityWeakReference.get();
        if (activity == null || activity.isFinishing()) return;

        // activity.mLoadingIndicator.setVisibility(View.INVISIBLE);
        if (movies != null) {
            activity.showMovieData();
            activity.movieAdapter.setMoviePOJO(movies);
            activity.movieRecyclerView.scrollToPosition(0);
        } else {
            activity.showErrorMessage();
        }

    }
        public boolean checkWifiOnAndConnected () {
            WifiManager wifiMgr = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            if (wifiMgr.isWifiEnabled()) {
                WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
                return wifiInfo.getNetworkId() != -1;
            } else {
                Toast.makeText(this, "Wifi is not connected", Toast.LENGTH_LONG).show();
                return false; // Wi-Fi adapter is OFF
            }
        }
    }

