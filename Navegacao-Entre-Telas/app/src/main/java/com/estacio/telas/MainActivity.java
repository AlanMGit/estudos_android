package com.estacio.telas;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.estacio.telas.GenericDroid.GenericDroid;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GenericDroid gd = new GenericDroid(this);
    private Button btnSegundaTela;
    private Button btnBrowser;
    private EditText edtValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditEButton();
    }

    private void EditEButton() {
        edtValor = (EditText) findViewById(R.id.edtMensagem);
        btnSegundaTela = (Button) findViewById(R.id.btnSegunda);
        btnBrowser = (Button) findViewById(R.id.btnBrowser);
        btnBrowser.setOnClickListener(this);
        btnSegundaTela.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnBrowser:
                if(gd.isOnline() == true) {
                    String siteEdit = edtValor.getText().toString();
                    Intent irParaNavegador = new Intent(Intent.ACTION_VIEW);
                    Uri site = Uri.parse("http://" + siteEdit);
                    irParaNavegador.setData(site);
                    startActivity(irParaNavegador);
                }
                break;

            case R.id.btnSegunda:
                String mensagem = edtValor.getText().toString();
                Intent intentTela = new Intent(getApplicationContext(), ActivitySegundaTela.class);
                intentTela.putExtra("chave", mensagem);
                startActivity(intentTela);
                break;
        }
    }
}