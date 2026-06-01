package com.example.quoteapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FavoriteActivity extends AppCompatActivity {

    TextView tvFavoriteQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        tvFavoriteQuote = findViewById(R.id.tvFavoriteQuote);

        SharedPreferences preferences =
                getSharedPreferences("Quotes", MODE_PRIVATE);

        String favoriteQuote = preferences.getString(
                "favoriteQuote",
                "No favorite quote saved yet."
        );

        tvFavoriteQuote.setText(favoriteQuote);
    }
}