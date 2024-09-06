package com.example.financierael_transador.Servicios;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static ApiInterface API_SERVICE;

    public static ApiInterface getApiService() {
        // Configuración del interceptor para el registro de solicitudes/respuestas
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Configuración del cliente HTTP con el interceptor
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        // Configuración de Retrofit
        String baseUrl = "http://192.168.100.20:8080/";

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            API_SERVICE = retrofit.create(ApiInterface.class);
        }
        return API_SERVICE;
    }
}

