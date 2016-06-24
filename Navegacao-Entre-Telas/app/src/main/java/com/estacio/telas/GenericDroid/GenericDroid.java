package com.estacio.telas.GenericDroid;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Alan Martins on 26/10/2015.
 */
public class GenericDroid{
    private Activity activity;
    //COSTRUTOR
    public GenericDroid(Activity activity) {
        this.activity = activity;
    }

    //VERIFICANDO SE EXISTE CONEXAO
    public boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }else {
            mensagemToast("Verifique sua conexão");
            return false;
        }
    }

    //MENSAGEM DE TOAST (MENSAGEM QUE DESEJA SER MOSTRADA)
    public void mensagemToast(String mensagem){
        Toast.makeText(activity, mensagem, Toast.LENGTH_SHORT).show();
    }
}
