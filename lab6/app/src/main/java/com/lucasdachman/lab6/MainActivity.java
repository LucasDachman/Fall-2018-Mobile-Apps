package com.lucasdachman.lab6;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((SeekBar)findViewById(R.id.seekBar)).setOnSeekBarChangeListener(this);
    }


    /** onClick Methods **/

    public void navPrev(View view) {

    }

    public void navNext(View view) {

    }

    /** Helper functions **/

    public void setPage(int index) {

    }

    /** Seek Bar implementations **/

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
