package com.blessgame.jumper.engine;

import com.blessgame.jumper.Elements.Canos;
import com.blessgame.jumper.Elements.Passaro;

/**
 * Created by Alan on 20/12/2015.
 */
public class verificadordeColisao {
    private Passaro passaro;
    private Canos canos;

    public verificadordeColisao(Passaro passaro, Canos canos) {
        this.passaro = passaro;
        this.canos = canos;
    }

    public boolean temColisao(){
        return canos.temColisaoComPassaro(passaro);
    }
}
