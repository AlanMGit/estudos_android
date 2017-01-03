package com.hellotts;

import android.os.Bundle;
import android.speech.RecognitionListener;

/**
 * Created by Alan on 24/12/2016.
 */

public class BaseRecognitionListener implements RecognitionListener {

    @Override
    public void onReadyForSpeech(Bundle bundle) { }

    //Indica que o usuário começou a falar
    @Override
    public void onBeginningOfSpeech() { }

    @Override
    public void onRmsChanged(float v) { }

    //Buffer que vai sendo montado à medida que o usuário vai falando
    //Não é garantido que este método seja chamado
    @Override
    public void onBufferReceived(byte[] bytes) { }

    //Indica que o usuário terminou de falar
    @Override
    public void onEndOfSpeech() { }

    //Indica que ocorreu um erro no reconhecimento de voz
    @Override
    public void onError(int i) { }

    //Chamado quando a aplicação está pronta para receber o comando de voz
    @Override
    public void onResults(Bundle bundle) { }

    //Chamado para entregar os resultados para a aplicação
    @Override
    public void onPartialResults(Bundle bundle) { }

    //Indica que o nível de som do áudio mudou
    @Override
    public void onEvent(int i, Bundle bundle) { }
}
