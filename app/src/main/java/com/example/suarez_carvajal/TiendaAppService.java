package com.example.suarez_carvajal;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class TiendaAppService {
    private static final String URL_SERVICE = "https://tiendaapp-b2cb5-default-rtdb.firebaseio.com/";
    private static Retrofit instacia =null;

    public static Retrofit obtenerInstancia(){
        if(instacia==null){
            instacia = new Retrofit.Builder()
                    .baseUrl(URL_SERVICE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instacia;
    }
}
