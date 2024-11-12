package com.sr.a6semprojectfinal;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.sr.a6semprojectfinal.APIRequests.GetExploreData;

public class Launcher extends AppCompatActivity {
    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.main_launcher);
        handler();


    }

    public void handler(){

        VideoView view = findViewById(R.id.intro_animation);
        view.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash_screen));
        view.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Intent intent = new Intent(Launcher.this,Main.class);
                startActivity(intent);
                finish();

            }
        });

        view.start();

    }

}
