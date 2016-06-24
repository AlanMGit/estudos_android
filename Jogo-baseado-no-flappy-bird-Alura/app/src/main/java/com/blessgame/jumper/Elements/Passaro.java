package com.blessgame.jumper.Elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.blessgame.jumper.R;
import com.blessgame.jumper.graphic.Cores;
import com.blessgame.jumper.graphic.Tela;

/**
 * Created by Alan on 20/12/2015.
 */
public class Passaro {
    public static final int RAIO = 50;
    public static final float X = 100;
    private static final Paint COR = Cores.getCorDoPassaro();
    private float altura;
    private Tela tela;
    private Bitmap imgPassaro;

    public Passaro(Tela tela, Context context) {
        this.altura = 100;
        this.tela = tela;
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird);
        this.imgPassaro = Bitmap.createScaledBitmap(bp, RAIO * 2, RAIO * 2, false);
    }

    public void desenhaNoCanvas(Canvas canvas){
        canvas.drawCircle(X, altura, RAIO, COR);
        //Colocando imagem do passaro
        //canvas.drawBitmap(imgPassaro, X - RAIO, altura - RAIO, null);
    }

    public void cai() {
        boolean chegouNoChao = altura + RAIO > tela.getAltura();
        if(!chegouNoChao){
            this.altura += 7;
        }
    }

    public void pula() {
        if (altura - RAIO > 0){
            this.altura -= 200;
        }
    }

    public float getAltura() {
        return this.altura;
    }
}
