package com.trial.audiovideoplay;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    EditText urlInput;
    MediaPlayer mediaPlayer;
    Uri audioUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        urlInput = findViewById(R.id.urlInput);

        Button openUrl = findViewById(R.id.openUrlBtn);
        Button openFile = findViewById(R.id.openFileBtn);
        Button play = findViewById(R.id.playBtn);
        Button pause = findViewById(R.id.pauseBtn);
        Button stop = findViewById(R.id.stopBtn);
        Button restart = findViewById(R.id.restartBtn);

        // 🎬 Open URL
        openUrl.setOnClickListener(v -> {
            String url = urlInput.getText().toString();
            videoView.setVideoURI(Uri.parse(url));
            videoView.start();
        });

        // 🎵 Open Audio File
        openFile.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);
        });

        // ▶️ Play
        play.setOnClickListener(v -> {
            if (videoView != null) videoView.start();
            if (mediaPlayer != null) mediaPlayer.start();
        });

        // ⏸ Pause
        pause.setOnClickListener(v -> {
            if (videoView.isPlaying()) videoView.pause();
            if (mediaPlayer != null && mediaPlayer.isPlaying()) mediaPlayer.pause();
        });

        // ⏹ Stop
        stop.setOnClickListener(v -> {
            if (videoView.isPlaying()) videoView.stopPlayback();
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        });

        // 🔄 Restart
        restart.setOnClickListener(v -> {
            if (videoView != null) videoView.seekTo(0);
            if (mediaPlayer != null) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
            }
        });
    }

    // 🎵 Audio Result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            audioUri = data.getData();

            mediaPlayer = MediaPlayer.create(this, audioUri);
            mediaPlayer.start();
        }
    }
}