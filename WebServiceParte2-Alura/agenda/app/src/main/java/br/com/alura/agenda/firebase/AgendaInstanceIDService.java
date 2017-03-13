package br.com.alura.agenda.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import br.com.alura.agenda.retrofit.RetrofitInicializador;
import br.com.alura.agenda.services.DispositivoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alan on 08/03/17.
 */

public class AgendaInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();
        enviaTokenParaServidor(token);

    }

    private void enviaTokenParaServidor(final String token) {

        Call<Void> voidCall = new RetrofitInicializador().getDispositivoService().enviaToken(token);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("token", "Token enviado ".concat(token));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("token", "Erro ao enviar token ".concat(t.getMessage()));
            }
        });

    }
}
