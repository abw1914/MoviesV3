package project.udacity.awillis.moviesv3;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParseMovieData {

    ArrayList<MoviePOJO> getMovieDataJSON(String movieJsonString) throws JSONException {
        ArrayList<MoviePOJO> moviePOJOS = new ArrayList<>();

        final String RESULTS = "results";
        final String ID = "id";
        final String TITLE = "title";
        final String ORIGINAL_TITLE = "original_title";
        final String VOTE_COUNT = "vote_count";
        final String VOTE_AVERAGE = "vote_average";
        final String RELEASE_DATE = "release_date";
        final String OVERVIEW = "overview";
        final String POPULARITY = "popularity";
        final String POSTER_PATH = "poster_path";
        final String BACKDROP_PATH = "backdrop_path";
        final String VIDEO = "video";
        final String ORIGINAL_LANGUAGE = "original_language";
        final String GENRE_IDS = "genre_ids";
        final String ADULT = "adult";


        JSONObject jsonObject = new JSONObject(movieJsonString);
          JSONArray jsonArray = jsonObject.getJSONArray(RESULTS);
          int movieLength = jsonArray.length();
          ArrayList<MoviePOJO> movieList = new ArrayList<>();

        for (int i = 0; i < movieLength; i++) {
            JSONObject movieObject = jsonArray.getJSONObject(i);

            int id = movieObject.getInt(ID);
            String title = movieObject.getString(TITLE);
            String originalTitle = movieObject.getString(ORIGINAL_TITLE);
            int voteCount = movieObject.getInt(VOTE_COUNT);
            String voteAverage = movieObject.getString(VOTE_AVERAGE);
            String releaseDate = movieObject.getString(RELEASE_DATE);
            String overview = movieObject.getString(OVERVIEW);
            int popularity = movieObject.getInt(POPULARITY);
            String posterPath = movieObject.getString(POSTER_PATH);
            String backdrop = movieObject.getString(BACKDROP_PATH);
            boolean video = movieObject.getBoolean(VIDEO);
            String originalLanguage = movieObject.getString(ORIGINAL_LANGUAGE);
            int genreId = movieObject.getInt(GENRE_IDS);
            boolean adult = movieObject.getBoolean(ADULT);


            //might need to loop through this ...
            moviePOJOS.add(id, new MoviePOJO(title,
                    originalTitle,
                    voteCount,
                    voteAverage,
                    releaseDate,
                    overview,
                    popularity,
                    posterPath,
                    backdrop,
                    video,
                    originalLanguage,
                    genreId,
                    adult));

        }
            return movieList;
        }
}
