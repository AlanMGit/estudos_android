package br.com.alura.agenda.retrofit;

import br.com.alura.agenda.services.AlunoService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Alan on 20/02/2017.
 */

public class RetrofitInicializador {

    private final Retrofit mRetrofit;
    private Object alunoService;

    public RetrofitInicializador() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.105:8080/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }


    public AlunoService getAlunoService() {
        return mRetrofit.create(AlunoService.class);
    }
}
