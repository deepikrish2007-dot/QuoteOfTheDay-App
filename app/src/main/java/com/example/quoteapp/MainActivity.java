package com.example.quoteapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuote;
    private Button btnNewQuote, btnShare, btnFavorite, btnViewFavorite;

    private final String[] quotes = {
            "Believe you can and you're halfway there. – Theodore Roosevelt",
            "Success is not final, failure is not fatal. – Winston Churchill",
            "Dream big and dare to fail. – Norman Vaughan",
            "Stay hungry, stay foolish. – Steve Jobs",
            "The future depends on what you do today. – Mahatma Gandhi",
            "Never stop learning because life never stops teaching.",
            "Push yourself, because no one else is going to do it for you.",
            "Great things never come from comfort zones.",
            "Your only limit is your mind.",
            "Every day is a second chance.",
            "Do something today that your future self will thank you for.",
            "Small steps every day lead to big results.",
            "Hard work beats talent when talent doesn't work hard.",
            "Don't watch the clock; do what it does. Keep going.",
            "Success starts with self-belief."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuote = findViewById(R.id.tvQuote);
        btnNewQuote = findViewById(R.id.btnNewQuote);
        btnShare = findViewById(R.id.btnShare);
        btnFavorite = findViewById(R.id.btnFavorite);
        btnViewFavorite = findViewById(R.id.btnViewFavorite);

        // Show a random quote when app starts
        showRandomQuote();

        // New Quote Button
        btnNewQuote.setOnClickListener(v -> showRandomQuote());

        // Share Quote Button
        btnShare.setOnClickListener(v -> {
            String quote = tvQuote.getText().toString();

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, quote);

            startActivity(Intent.createChooser(
                    shareIntent,
                    "Share Quote Via"
            ));
        });

        // Save Favorite Quote
        btnFavorite.setOnClickListener(v -> {
            String quote = tvQuote.getText().toString();

            SharedPreferences preferences =
                    getSharedPreferences("Quotes", MODE_PRIVATE);

            preferences.edit()
                    .putString("favoriteQuote", quote)
                    .apply();

            Toast.makeText(
                    MainActivity.this,
                    "❤️ Quote Saved!",
                    Toast.LENGTH_SHORT
            ).show();
        });

        // Open Favorite Screen
        btnViewFavorite.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    FavoriteActivity.class
            );
            startActivity(intent);
        });
    }

    private void showRandomQuote() {
        Random random = new Random();
        int index = random.nextInt(quotes.length);
        tvQuote.setText(quotes[index]);
    }
}