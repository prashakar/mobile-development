package com.example.prash.mobile_labs;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;

public class Lab10Activity extends AppCompatActivity {

    private SurfaceView surface = null;
    private SurfaceHolder holder = null;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab10);

        surface = (SurfaceView)findViewById(R.id.surfaceView);
        holder = surface.getHolder();

        ArrayAdapter<String> fileNameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.fileNames));

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (player != null){
                    holder = surface.getHolder();
                    player.setDisplay(holder);
                    player.release();
                }

                String selectedFile = (String) adapterView.getItemAtPosition(i);

                if (selectedFile.endsWith(".mp4")){
                    // video file
                    playVideo(selectedFile);
                } else {
                    // audio file
                    playAudio(selectedFile);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner.setAdapter(fileNameAdapter);



    }

    private void playAudio(String fileName){
        player = new MediaPlayer();
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + fileName.substring(0, fileName.length() - 4));
        try {
            player.setDataSource(this, uri);
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start();
                }
            });
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playVideo(String fileName){
        player = new MediaPlayer();
        player.setScreenOnWhilePlaying(true);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + fileName.substring(0, fileName.length() - 4));
        try {
            player.setDataSource(this, uri);
            player.setDisplay(holder);
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    int width=player.getVideoWidth();
                    int height=player.getVideoHeight();

                    if ((width != 0) && (height != 0)) {
                        holder.setFixedSize(width, height);
                        Log.d("VideoPlayer", "Playing video...");
                        player.start();
                    }
                }
            });
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play(View view){
        player.start();

    }

    public void pause(View view){
        player.pause();
    }
}
