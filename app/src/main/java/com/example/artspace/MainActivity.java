package com.example.artspace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private static final String KEY_INDEX = "artwork_index";

    private TextView tvCounter, tvTitle, tvArtistYear;
    private ImageView ivArtwork;
    private Button btnPrevious, btnNext;

    private List<Artwork> artworks;
    private int currentIndex = 0;

    // كلاس داخلي لتمثيل بيانات كل عمل فني داخل نفس الملف
    private static class Artwork {
        final int imageResId;
        final String title;
        final String artist;
        final String year;
        final String websiteUrl;

        Artwork(int imageResId, String title, String artist, String year, String websiteUrl) {
            this.imageResId = imageResId;
            this.title = title;
            this.artist = artist;
            this.year = year;
            this.websiteUrl = websiteUrl;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvCounter = (TextView) findViewById(R.id.tvCounter);
        ivArtwork = (ImageView) findViewById(R.id.ivArtwork);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvArtistYear = (TextView) findViewById(R.id.tvArtistYear);
        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnNext = (Button) findViewById(R.id.btnNext);

        setupArtworks();

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateArtworkDisplay();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex < artworks.size() - 1) {
                    currentIndex++;
                    updateArtworkDisplay();
                }
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0) {
                    currentIndex--;
                    updateArtworkDisplay();
                }
            }
        });

        ivArtwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Artwork selected = artworks.get(currentIndex);
                Intent intent = new Intent(MainActivity.this, ArtworkDetailsActivity.class);
                intent.putExtra("EXTRA_TITLE", selected.title);
                intent.putExtra("EXTRA_ARTIST", selected.artist);
                intent.putExtra("EXTRA_YEAR", selected.year);
                intent.putExtra("EXTRA_URL", selected.websiteUrl);
                startActivity(intent);
            }
        });
    }

    private void setupArtworks() {
        artworks = new ArrayList<Artwork>();
        artworks.add(new Artwork(R.drawable.art_1, "Deep Field Stellar Deaths", "Smacs Carin", "2021", "https://www.nasa.gov"));
        artworks.add(new Artwork(R.drawable.art_2, "Starry Night Over Space", "Elena Vance", "2020", "https://www.artic.edu"));
        artworks.add(new Artwork(R.drawable.art_3, "Golden Horizon", "Tariq Mansour", "2022", "https://www.metmuseum.org"));
        artworks.add(new Artwork(R.drawable.art_4, "Swirling Cosmic Waves", "Maya Haddad", "2018", "https://www.louvre.fr"));
        artworks.add(new Artwork(R.drawable.art_5, "Echoes of Light", "Liam Brooks", "2023", "https://www.nationalgallery.org.uk"));
        artworks.add(new Artwork(R.drawable.art_6, "Hubble Ngc Flat Final", "Sonay Billan", "2019", "https://hubblesite.org"));
    }

    private void updateArtworkDisplay() {
        Artwork current = artworks.get(currentIndex);

        tvCounter.setText((currentIndex + 1) + " / " + artworks.size());
        ivArtwork.setImageResource(current.imageResId);
        tvTitle.setText(current.title);
        tvArtistYear.setText(current.artist + " (" + current.year + ")");

        btnPrevious.setEnabled(currentIndex > 0);
        btnNext.setEnabled(currentIndex < artworks.size() - 1);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, currentIndex);
    }
}
