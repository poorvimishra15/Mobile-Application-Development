package com.trial.androidgalleryapp;

import android.app.AlertDialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.Date;

public class DetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name, path, size, date;
    Button deleteBtn;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.name);
        path = findViewById(R.id.path);
        size = findViewById(R.id.size);
        date = findViewById(R.id.date);
        deleteBtn = findViewById(R.id.deleteBtn);

        String filePath = getIntent().getStringExtra("path");
        file = new File(filePath);

        imageView.setImageBitmap(BitmapFactory.decodeFile(filePath));
        name.setText("Name: " + file.getName());
        path.setText("Path: " + file.getAbsolutePath());
        size.setText("Size: " + file.length() + " bytes");
        date.setText("Date: " + new Date(file.lastModified()).toString());

        deleteBtn.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Delete")
                    .setMessage("Are you sure?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        file.delete();
                        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}