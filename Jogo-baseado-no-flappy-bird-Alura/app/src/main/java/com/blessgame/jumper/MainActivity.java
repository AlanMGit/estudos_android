package com.blessgame.jumper;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.blessgame.jumper.engine.Game;

public class MainActivity extends Activity {
    private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout container = (FrameLayout)findViewById(R.id.container);
        game = new Game(this);
        //Aqui estamos criando a tela
        container.addView(game);
    }

    @Override
    protected void onResume() {
        super.onResume();
        game.inicia();
        new Thread(game).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        game.pause();
    }
}
