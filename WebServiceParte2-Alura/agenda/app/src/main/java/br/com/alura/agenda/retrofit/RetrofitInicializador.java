package br.com.alura.agenda.retrofit;

import br.com.alura.agenda.services.DispositivoService;
import br.com.alura.agenda.services.AlunoService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Alan on 20/02/2017.
 */

public class RetrofitInicializador {

    private final Retrofit mRetrofit;
    private Object alunoService;
    private Object dispositivoService;

    public RetrofitInicializador() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.105:8080/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(builder.build())
                .build();
    }


    public AlunoService getAlunoService() {
        return mRetrofit.create(AlunoService.class);
    }

    public DispositivoService getDispositivoService() {
        return mRetrofit.create(DispositivoService.class);
    }
}
