package com.desenhoclassecanvas;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Alan on 01/07/2016.
 */
public class MinhaView extends View {

    private Paint pincelVermelho;
    private Paint pincelPreto;
    private Paint pincelAzul;

    public MinhaView(Context context) {
        super(context, null);
    }

    public MinhaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.LTGRAY);

        //Vermelho
        pincelVermelho = new Paint();
        pincelVermelho.setARGB(255,255,0,0);

        //Preto
        pincelPreto = new Paint();
        pincelPreto.setARGB(255,0,0,0);

        //Azul
        pincelAzul = new Paint();
        pincelAzul.setARGB(255,0,0,255);

        //Configura a View para receber foco e eventos do teclado
        setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Desenha um Quadrado
        canvas.drawRect(toPixels(20),toPixels(20),toPixels(200),toPixels(200), pincelAzul);

        //Desenha uma linha
        canvas.drawLine(200,200,400,400, pincelPreto);

        //Desenha Circulo
        canvas.drawCircle(400,400,100,pincelVermelho);
    }

    //Converte o valor em dp para pixels
    public float toPixels(float dip){
        Resources r = getContext().getResources();
        float densidade = r.getDisplayMetrics().density; //Densitade da tela
        int px = (int)(dip * densidade + 0.5f);
        return dip;
    }
}
