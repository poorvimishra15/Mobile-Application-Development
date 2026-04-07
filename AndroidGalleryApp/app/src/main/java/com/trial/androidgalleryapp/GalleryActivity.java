package com.trial.androidgalleryapp;

import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<File> imageList;
    ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        File folder = new File(Environment.getExternalStorageDirectory() + "/MyImages");
        File[] files = folder.listFiles();

        imageList = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                imageList.add(file);
            }
        }

        adapter = new ImageAdapter(this, imageList);
        recyclerView.setAdapter(adapter);
    }
}