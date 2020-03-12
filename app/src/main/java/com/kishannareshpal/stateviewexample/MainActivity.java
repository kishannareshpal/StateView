package com.kishannareshpal.stateviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import com.kishannareshpal.stateview.AnimationType;
import com.kishannareshpal.stateview.ComponentGravity;
import com.kishannareshpal.stateview.State;
import com.kishannareshpal.stateview.StateView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context ctx = this;

        StateView sv = findViewById(R.id.sv);
        sv.titleFont("lobster_regular.ttf");
        sv.gravity(ComponentGravity.LEFT)
                .onActionButtonClick((stateView, actionButton) -> {
                    vibrate();
                });
    }


    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            VibrationEffect vibe = VibrationEffect.createOneShot(40, 10);
            vibrator.vibrate(vibe);
        } else {
            vibrator.vibrate(40);
        }
    }
}

