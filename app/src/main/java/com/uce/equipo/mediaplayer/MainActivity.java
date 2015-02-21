package com.uce.equipo.mediaplayer;

import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
    public Button bplay,bstop,bsoundPool;
    public MediaPlayer mp;
    public SoundPool sp;
    public int flujodemusica=0;
    int posicion = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bplay = (Button)findViewById(R.id.button);
        bstop = (Button)findViewById(R.id.button2);
        bsoundPool = (Button)findViewById(R.id.button3);
        sp = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        flujodemusica= sp.load(this,R.raw.sonido_acierto,1);

    }

    public void play (View v) {
        mp= MediaPlayer.create(this, R.raw.main_theme);
        mp.start();
    }
    public void stop(View v) {
        if (mp != null) {
            mp.stop();
           posicion = 0;
        }
    }
    public void play2(View v) {
        sp.play(flujodemusica, 1, 1, 0, 0, 1);
    }

    public void pausa(View v) {
        if (mp != null && mp.isPlaying()) {
            posicion = mp.getCurrentPosition();
            mp.pause();
        }
    }

    public void continuar(View v) {
        if (mp != null && mp.isPlaying() == false) {
            mp.seekTo(posicion);
            mp.start();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
