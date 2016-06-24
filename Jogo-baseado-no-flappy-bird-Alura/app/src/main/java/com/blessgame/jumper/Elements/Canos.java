package com.blessgame.jumper.Elements;

import android.content.Context;
import android.graphics.Canvas;

import com.blessgame.jumper.graphic.Tela;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Alan on 20/12/2015.
 */
public class Canos {
    private final List<Cano> canos = new ArrayList<Cano>();
    private static final int DISTANCIA_ENTRE_CANOS = 500;
    private static final int QUANTIDADE_DE_CANOS = 5;
    private Tela tela;
    private Pontuacao pontuacao;
    private Context context;

    public Canos(Tela tela, Pontuacao pont, Context context) {
        int posicao = 400;
        this.tela = tela;
        this.pontuacao = pont;
        this.context = context;

        for (int i= 0; i < QUANTIDADE_DE_CANOS; i++){
            posicao += DISTANCIA_ENTRE_CANOS;
            Cano cano = new Cano(tela, posicao, context);
            canos.add(cano);
        }
    }

    public void move() {
        /*
        for (Cano cano : canos){
            cano.move();
        }
        */
        ListIterator<Cano> iterator = canos.listIterator();
        while (iterator.hasNext()){
            Cano cano = iterator.next();
            cano.move();

            if(cano.saiuDaTela()){
                pontuacao.aumenta();
                iterator.remove();
                Cano outroCano = new Cano(tela, getMaximo() + DISTANCIA_ENTRE_CANOS, context);
                iterator.add(outroCano);
            }
        }


    }

    public void desenhanoCanvas(Canvas canvas) {
        for (Cano cano : canos)
        {
            cano.desenhanoCanvas(canvas);
        }
    }

    public int getMaximo() {
        int maximo = 0;
        for(Cano cano : canos){
            maximo = Math.max(cano.getPosicao(), maximo);
        }
        return maximo;
    }

    public boolean temColisaoComPassaro(Passaro passaro) {
        for (Cano cano : canos){
            if(cano.temColisaoHorizontalComPassaro(passaro) && cano.temColisaoVerticalComPassaro(passaro)){
                return true;
            }
        }

        return false;
    }
}
