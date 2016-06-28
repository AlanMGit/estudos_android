package com.imagemove;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Alan on 27/06/2016.
 */
public class TouchScreenView extends View {

    private static final String TAG = "LOG";
    private Drawable img;
    int x, y;
    private boolean selecionou;
    private int larguraTela;
    private int alturaTela;
    private int larguraImg;
    private int alturaImg;

    public TouchScreenView(Context context){
        super(context, null);
        //Recupera a Imagem
        img = context.getResources().getDrawable(R.drawable.ic_launcher);

        //Recupera a largura e altura da imagem
        larguraImg = img.getIntrinsicWidth();
        alturaImg = img.getIntrinsicHeight();

        //Configura a view para receber foco e tratar eventos deo teclado
        setFocusable(true);
    }

    @Override
    //Chamada quando a tela é redimensionada ou inciada
    protected void onSizeChanged(int width, int height, int oldw, int oldh){
        super.onSizeChanged(width,height,oldw,oldh);
        this.larguraTela = width;
        this.alturaTela = height;

        x = width / 2 - (larguraTela / 2);
        y = height / 2 - (alturaTela / 2);
    }

    //Desenha na Tela

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //FundoBranco
        Paint pincel = new Paint();
        pincel.setColor(Color.WHITE);
        canvas.drawRect(0, 0, larguraTela, alturaTela, pincel);
        //Define o limite
        img.setBounds(x, y, x + larguraTela, y + alturaTela);

        //Desenha a imagem
        img.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y =event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //Inicia o movimento se pressionou a imagem
                selecionou
                         = img.copyBounds().contains((int) x, (int) y);
                break;
            case MotionEvent.ACTION_MOVE:
                //Arrasta
                if (selecionou){
                    this.x = (int) x - (larguraImg / 2);
                    this.y = (int) y - (alturaImg / 2);
                }
                break;
            case MotionEvent.ACTION_UP:
                selecionou = false;
                break;
        }

        invalidate();
        return true;
    }
}
