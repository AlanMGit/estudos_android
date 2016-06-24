package com.estacio.telas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.estacio.telas.GenericDroid.GenericDroid;

/**
 * Created by Usuario on 26/10/2015.
 */
public class ActivitySegundaTela extends AppCompatActivity{
    GenericDroid genericDroid = new GenericDroid(this);
    private EditText edtMensagem;
    private TextView mostrarTexto;
    private Button btnProximaTela;
    private String m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        iniciarComponente();

        //Verificando se veio algum parametro da tela principal
        Bundle params = getIntent().getExtras();
        if(params != null){
            m = params.getString("chave");
            mostrarTexto.setText(m);
        }

        btnProximaTela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               genericDroid.mensagemToast(edtMensagem.getText().toString());
            }
        });
    }
    public void iniciarComponente(){
        edtMensagem = (EditText)findViewById(R.id.edt1);
        mostrarTexto = (TextView)findViewById(R.id.textview);
        btnProximaTela = (Button)findViewById(R.id.btnProxima);
    }
}
