package com.hellotts;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.MediaPlayer;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private static final String TAG = "AUDIO";
    private static final int ACTION_CHECK_DATE_CODE = 1;
    private static final int ACTION_RECOGNIZE       = 200;

    private TextView        mTvMensagem;
    private ListView        mListaView;
    private TextToSpeech    mTextToSpeech;

    private Button mBtnFalarTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        capturarComponentes();

    }

    @Override
    public void onInit(int i) {
        Log.d(TAG, "ENGINE TTS INICIALIZADA COM SUCESSO: " + Locale.getDefault());
        //Deixe inglês por padrão
        mTextToSpeech.setLanguage(Locale.getDefault());
    }

    private void capturarComponentes() {

        mTvMensagem     = (TextView) findViewById(R.id.tMsg);
        mListaView      = (ListView) findViewById(R.id.lista);
        mBtnFalarTexto  = (Button) findViewById(R.id.btn_edt_falar_texto);

        mTextToSpeech   = new TextToSpeech( this, this );
        mBtnFalarTexto.setOnClickListener( aoClicarButton );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hello_tts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_pt_br) {
            Locale locale = new Locale("pt", "BR");
            mTextToSpeech.setLanguage(locale);
            return true;
        } else if (id == R.id.action_en_us) {
            Locale locale = Locale.ENGLISH;
            mTextToSpeech.setLanguage(locale);
            return true;
        } else if (id == R.id.action_check_date) {
            //Verifica se o pacote de dados do tts está instalado
            Intent checkIntent = new Intent();
            checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
            startActivityForResult(checkIntent, ACTION_CHECK_DATE_CODE);
            return true;
        } else if (id == R.id.action_install_date) {
            //Instala o pacote de dados
            Intent installIntent = new Intent();
            installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            startActivity(installIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickReconhecerVoz(View view) {

        //Verifica se o Android suporta a intent de reconhecimento de voz
        PackageManager pm = getPackageManager();

        List<ResolveInfo> activities = pm.queryIntentActivities(new Intent
                (RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);

        if (activities.size() != 0) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Fale algo");
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR");
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);
            startActivityForResult(intent, ACTION_RECOGNIZE);
        } else {
            Toast.makeText(this, "Reconhecimento de voz indisponível", Toast.LENGTH_SHORT).show();
        }

    }

//    public void onClickSalvar(View view){
//        String s = tMsg.getText().toString();
//        //Sintetiza a voz para o Arquivo
//        File file = new File();
//        HashMap<String, String> params = new HashMap<>();
//        tts.synthesizeToFile(s, params, file.getAbsolutePath());
//        Toast.makeText(this, "Voz salva em arquivo: " + file, Toast.LENGTH_SHORT).show();
//    }
//
//    public void onClickFalarArquivo(View view){
//
//        File file = new File();
//
//        if (file.exists()){
//
//            try{
//
//                MediaPlayer mp = new MediaPlayer();
//                mp.setDataSource(file.getAbsolutePath());
//                mp.prepare();
//                mp.start();
//            }catch (Exception e){
//                Log.e(TAG, "Erro ao tocar: " + e.getMessage());
//            }
//
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTION_CHECK_DATE_CODE) {

            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL) {
                Toast.makeText(MainActivity.this, "Pacote de dados de voz OK", Toast.LENGTH_SHORT).show();
            } else {

                //Falta pacote, solicita a instalacao
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }

        if (requestCode == ACTION_RECOGNIZE) {
            if (resultCode == RESULT_OK) {
                //Recupera as possíveis palavras que foran pronunciadas
                ArrayList<String> words = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                mListaView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, words));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Libera os recursos da engine do TTS
        mTextToSpeech.shutdown();
    }

    private View.OnClickListener aoClicarButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if ( view.getId() == R.id.btn_edt_falar_texto ){

                String mensagemDigitada = mTvMensagem.getText().toString();

                if ( mensagemDigitada.isEmpty() ) {
                    mTvMensagem.setError("Obrigatório");
                    mTextToSpeech.speak("Por favor, digite uma mensagem", TextToSpeech.QUEUE_FLUSH, null);
                    mTvMensagem.setText("");
                    return;
                }

                mTextToSpeech.speak( mensagemDigitada, TextToSpeech.QUEUE_FLUSH, null );
                Log.d( TAG, "Speak: ".concat( mensagemDigitada ) );
                mTvMensagem.setText("");
            }else if ( view.getId() == R.id.btn_fale_algo_api){

                SpeechRecognizer stt = SpeechRecognizer.createSpeechRecognizer( MainActivity.this );
                stt.setRecognitionListener( new BaseRecognitionListener(){
                    public void onResults(Bundle results){

                        //Recupera as possíveis palavras que foram pronunciadas
                        ArrayList<String> words = results.getStringArrayList(
                                SpeechRecognizer.RESULTS_RECOGNITION);
                        mListaView.setAdapter( new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_list_item_1, words));
                    }
                });

                Intent intent = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH );
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR");
                stt.startListening( intent );

            }

        }
    };
}
