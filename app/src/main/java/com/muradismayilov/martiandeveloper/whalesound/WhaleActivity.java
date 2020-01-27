package com.muradismayilov.martiandeveloper.whalesound;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

public class WhaleActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whale);
        videoView = findViewById(R.id.videoView);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.whalesound);
        mediaPlayer.setLooping(true);
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }

        Uri address = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.underwater);
        videoView.setVideoURI(address);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        if (!(videoView.isPlaying())) {
            videoView.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
        videoView.pause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
        videoView.stopPlayback();
    }
}
