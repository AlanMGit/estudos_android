package com.blessgame.jumper.Elements;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.blessgame.jumper.graphic.Cores;

/**
 * Created by Alan on 20/12/2015.
 */
public class Pontuacao {
    private static final Paint COR_DA_PONTUACAO = Cores.getCorDaPontuacao();
    private int pontos = 0;

    public void desenhaNoCanvas(Canvas canvas) {
        canvas.drawText(String.valueOf(pontos), 100, 100, COR_DA_PONTUACAO);
    }

    public void aumenta() {
        pontos++;
    }
}
