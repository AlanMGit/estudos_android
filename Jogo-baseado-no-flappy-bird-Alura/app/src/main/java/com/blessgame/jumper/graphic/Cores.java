package com.blessgame.jumper.graphic;

import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Created by Alan on 20/12/2015.
 */
public class Cores {

    private static Paint corDoPassaro;
    private static Paint corDoCano;
    private static Paint corDaPontuacao;

    public static Paint getCorDoPassaro() {
        Paint paint = new Paint();
        paint.setColor(0xFFFF0000);
        return paint;
    }

    public static Paint getCorDoCano() {
        Paint paint = new Paint();
        paint.setColor(0xFF00FF00);
        return paint;
    }

    public static Paint getCorDaPontuacao() {
        Paint paint = new Paint();
        paint.setColor(0xFFFFFFFF);
        paint.setTextSize(80);

        paint.setTypeface(Typeface.DEFAULT_BOLD);

        paint.setShadowLayer(5,5 ,5, 0xFF000000);
        return paint;
    }
}
