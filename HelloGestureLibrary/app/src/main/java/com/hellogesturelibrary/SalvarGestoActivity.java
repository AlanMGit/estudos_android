package com.hellogesturelibrary;

import android.content.DialogInterface;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

public class SalvarGestoActivity extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener {

    private final File file = new File(Environment.getExternalStorageDirectory(), "gestures");
    private TextView mText;
    private GestureLibrary mGestureLibrary;
    private ImageView mImageView;
    private Gesture mGesture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvar_gesto);

        capturarComponentes();

    }

    private void capturarComponentes() {
        mText = (TextView) findViewById(R.id.text);
        mImageView = (ImageView) findViewById(R.id.img);
        GestureOverlayView overlayView = (GestureOverlayView) findViewById(R.id.gesture_view);
        overlayView.addOnGesturePerformedListener( this );

        mGestureLibrary = GestureLibraries.fromFile(file);
    }

    @Override
    public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {

        //Mostra o gesto em um ImageView
        int w = (int) gesture.getBoundingBox().width();
        int h = (int) gesture.getBoundingBox().height();
        Bitmap b = gesture.toBitmap(w,h,8, Color.GREEN);
        mImageView.setImageBitmap(b);
        gestureOverlayView.setGesture(gesture);
        this.mGesture = gesture;
        this.cadastrarAssinatura();
    }

    public void cadastrarAssinatura(){

        AlertDialog.Builder dialog = new AlertDialog.Builder( this );
        dialog.setTitle("Desejá salvar sua assinatura");
        dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (mGesture != null){
                    mGestureLibrary.addGesture(Calendar.getInstance().getTime().toString(), mGesture);
                    mGestureLibrary.save();
                    Toast.makeText(SalvarGestoActivity.this, "Gesto salvo", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        dialog.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alerta = dialog.create();
        alerta.show();
    }
}
