package com.blessgame.jumper.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.blessgame.jumper.Elements.Cano;
import com.blessgame.jumper.Elements.Canos;
import com.blessgame.jumper.Elements.Passaro;
import com.blessgame.jumper.Elements.Pontuacao;
import com.blessgame.jumper.R;
import com.blessgame.jumper.graphic.Tela;

/**
 * Created by Alan on 20/12/2015.
 */
public class Game extends SurfaceView implements Runnable, View.OnTouchListener{
    private boolean isRunning = true;
    private SurfaceHolder holder = getHolder();;
    private Canvas canvas;
    private Passaro passaro;
    private Bitmap background;
    private Tela tela;
    private Canos canos;
    private Pontuacao pontuacao;
    private verificadordeColisao  vfc;
    private Context context;

    public Game(Context context) {
        super(context);
        this.context = context;
        tela = new Tela(context);
        inicializaElementos();
        setOnTouchListener(this);
    }

    private void inicializaElementos() {
        passaro = new Passaro(tela, context);
        pontuacao = new Pontuacao();
        canos = new Canos(tela, pontuacao, context);

        //Colocando a imagem de fundo
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.fundo);
        background = Bitmap.createScaledBitmap(back, tela.getLargura(), tela.getAltura(), false);

    }

    @Override
    public void run() {
        while (isRunning)
        {
            if(!holder.getSurface().isValid()) continue;
            canvas = holder.lockCanvas();

            //Desenho dos componentes do jogo
            canvas.drawBitmap(background, 0, 0, null);
            passaro.desenhaNoCanvas(canvas);
            passaro.cai();

            canos.desenhanoCanvas(canvas);

            canos.move();
            pontuacao.desenhaNoCanvas(canvas);

            if(new verificadordeColisao(passaro, canos).temColisao()){
                isRunning = false;
            }


            holder.unlockCanvasAndPost(canvas);

        }
    }

    public void inicia() {
        isRunning = true;
    }

    public void pause() {
        isRunning = false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        passaro.pula();
        return false;
    }
}
