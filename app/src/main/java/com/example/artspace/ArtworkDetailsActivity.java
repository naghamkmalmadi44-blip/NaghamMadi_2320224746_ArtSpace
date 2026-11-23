package com.example.artspace;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ArtworkDetailsActivity extends Activity {

    private TextView tvArtworkDetails;
    private Button btnVisitWebsite, btnBack;
    private String websiteUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artwork_details);

        tvArtworkDetails = (TextView) findViewById(R.id.tvArtworkDetails);
        btnVisitWebsite = (Button) findViewById(R.id.btnVisitWebsite);
        btnBack = (Button) findViewById(R.id.btnBack);

        Intent intent = getIntent();
        String title = intent.getStringExtra("EXTRA_TITLE");
        String artist = intent.getStringExtra("EXTRA_ARTIST");
        String year = intent.getStringExtra("EXTRA_YEAR");
        websiteUrl = intent.getStringExtra("EXTRA_URL");

        if (websiteUrl == null || websiteUrl.trim().length() == 0) {
            websiteUrl = "https://www.nasa.gov";
        }

        String details = "Artwork Title: " + (title != null ? title : "") + "\n"
                + "Artist Name: " + (artist != null ? artist : "") + "\n"
                + "Creation Year: " + (year != null ? year : "");
        tvArtworkDetails.setText(details);

        btnVisitWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
                try {
                    startActivity(browserIntent);
                } catch (Exception e) {
                    Toast.makeText(ArtworkDetailsActivity.this, "Cannot open browser", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
