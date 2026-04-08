package com.trial.photo_upload;

import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trial.photo_upload.model.ImageModel;

import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ImageModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        recyclerView = findViewById(R.id.recyclerView);

        File folder = new File(getExternalFilesDir(null), "ImageManager");

        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                list.add(new ImageModel(file.getAbsolutePath()));
            }
        }

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(new ImageAdapter(this, list));
    }
}