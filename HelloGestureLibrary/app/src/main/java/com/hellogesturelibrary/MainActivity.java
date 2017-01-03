package com.hellogesturelibrary;

import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {

    private GestureLibrary mGestureLib;
    private TextView mText;
    private ImageView mImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        capturarComponents();
    }

    private void capturarComponents() {

        mText = (TextView) findViewById(R.id.text);
        mImg = (ImageView) findViewById(R.id.img);
        findViewById(R.id.abrir_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity( new Intent( MainActivity.this, SalvarGestoActivity.class) );

            }
        });

        //Configura o listener do GestureOverlayView
        GestureOverlayView overlayView = (GestureOverlayView) findViewById(R.id.gesture_view);
        overlayView.addOnGesturePerformedListener( this );

    }

    @Override
    public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {

        if (mGestureLib == null) {
            return;
        }

        //Faz a biblioteca de gestos reconhecer o movimento
        ArrayList<Prediction> predictions = mGestureLib.recognize(gesture);
        Prediction maxScore = null;

        for (Prediction p : predictions) {

            //Vamos aceitar somente escores maiores que 5
            if (p.score > 5.0) {

                if (maxScore == null || maxScore.score < p.score) {
                    maxScore = p;
                }
            }

        }

        //Se envontrou algum gesto com escore alto, vamos mostrar o texto
        if (maxScore != null) {

            //Se escore é maior que 5
            String desc = maxScore.name + ", score: " + maxScore.score;
            mText.setText(desc);

            //Mostra o gesto em um ImageView
            int w = (int) gesture.getBoundingBox().width();
            int h = (int) gesture.getBoundingBox().height();
            Bitmap b = gesture.toBitmap(w, h, 8, Color.GREEN);
            mImg.setImageBitmap(b);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Lê o arquivo de gestos do SD card
        readGestures();
    }

    public void readGestures(){

        File file = new File(Environment.getExternalStorageDirectory(), "gestures");
        if (file.exists()){
            mGestureLib = GestureLibraries.fromFile(file);
        }

        if (mGestureLib != null && mGestureLib.load()){
            Toast.makeText(this, "Biblioteca de gestos lida com sucesso",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
