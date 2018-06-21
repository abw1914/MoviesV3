package project.udacity.awillis.moviesv3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity{
    ImageView posterImage;
    TextView titleText;
    TextView yearText;

    TextView descriptionText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        Intent intent = getIntent();
         MoviePOJO moviePOJO = intent.getParcelableExtra(getString(R.string.extra_movie));
         UIBuild(moviePOJO);
    }


    public void UIBuild (MoviePOJO moviePOJO) {
        String Url = NetworkUtil.buildURLPoster(moviePOJO.getPoster());
        Picasso.get().load(Url).placeholder(R.drawable.no_poster).into(posterImage);

        titleText.setText(moviePOJO.getOriginal_title());
        yearText.setText(moviePOJO.getRelease());
        descriptionText.setText(moviePOJO.getDescription());
    }


}
