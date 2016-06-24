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
public class Cano {
    private static final Paint COR_DO_CANO = Cores.getCorDoCano();
    private static final int TAMANHO_DO_CANO = 350;
    private static final int LARGURA_DO_CANO = 180;
    private Tela tela;
    private int posicao;

    private int alturaDoCanoInferior;
    private int alturaDoCanoSuperior;
    private Bitmap canoInferior;
    private Bitmap canoSuperior;

    public Cano(Tela tela, int posicao, Context context) {
        this.tela = tela;
        this.posicao = posicao;
        alturaDoCanoInferior = tela.getAltura() - TAMANHO_DO_CANO - valorAleatorio();
        alturaDoCanoSuperior = 0 + TAMANHO_DO_CANO + valorAleatorio();

        //Cano inferior
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.cano);
        canoInferior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, alturaDoCanoInferior, false);

        //Cano Superir
        Bitmap bp_sup = BitmapFactory.decodeResource(context.getResources(), R.drawable.cano_sup);
        canoSuperior = Bitmap.createScaledBitmap(bp_sup, LARGURA_DO_CANO, alturaDoCanoSuperior, false);
    }

    private int valorAleatorio() {
        return (int)(Math.random() * 50);
    }

    public void desenhanoCanvas(Canvas canvas) {
        desenhaCanoSuperior(canvas);
        desenhaCanoInferior(canvas);
    }

    public void desenhaCanoSuperior(Canvas canvas){
        canvas.drawRect(posicao, 0, posicao + LARGURA_DO_CANO, alturaDoCanoSuperior, COR_DO_CANO);
        //Colocando Imagem do cano superior
        //canvas.drawBitmap(canoSuperior, posicao, 0, null);
    }

    public void desenhaCanoInferior(Canvas canvas) {
        canvas.drawRect(posicao, alturaDoCanoInferior, posicao + LARGURA_DO_CANO, tela.getAltura(), COR_DO_CANO);

        //Colocando Imagem do cano inferior
        //canvas.drawBitmap(canoInferior, posicao, alturaDoCanoInferior, null);
    }



    public void move() {
        this.posicao -= 5;
    }

    public boolean saiuDaTela() {
        return posicao + LARGURA_DO_CANO < 0;
    }

    public int getPosicao() {
        return posicao;
    }

    public boolean temColisaoHorizontalComPassaro(Passaro passaro) {
        return this.posicao < passaro.X + passaro.RAIO;
    }

    public boolean temColisaoVerticalComPassaro(Passaro passaro) {
        return passaro.getAltura() - passaro.RAIO < this.alturaDoCanoSuperior
                || passaro.getAltura() + passaro.RAIO > this.alturaDoCanoInferior;
    }
}
