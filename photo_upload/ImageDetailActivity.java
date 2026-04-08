package com.trial.photo_upload;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.Date;

public class ImageDetailActivity extends AppCompatActivity {

    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        ImageView image = findViewById(R.id.detailImage);
        TextView name = findViewById(R.id.txtName);
        TextView pathTxt = findViewById(R.id.txtPath);
        TextView size = findViewById(R.id.txtSize);
        TextView date = findViewById(R.id.txtDate);
        Button delete = findViewById(R.id.btnDelete);

        path = getIntent().getStringExtra("path");

        File file = new File(path);

        image.setImageBitmap(BitmapFactory.decodeFile(path));
        name.setText("Name: " + file.getName());
        pathTxt.setText("Path: " + path);
        size.setText("Size: " + file.length() / 1024 + " KB");
        date.setText("Date: " + new Date(file.lastModified()));

        delete.setOnClickListener(v -> showDialog());
    }

    private void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", (d, w) -> {
                    File file = new File(path);
                    file.delete();
                    finish();
                })
                .setNegativeButton("No", null)
                .show();
    }
}