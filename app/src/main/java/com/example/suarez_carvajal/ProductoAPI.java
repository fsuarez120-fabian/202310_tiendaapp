package com.example.suarez_carvajal;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductoAPI {

    @GET("productos.json")
    Call<Map<String , Producto>> obtenerTodo();

    @POST("productos.json")
    Call<Object> agregar(@Body Producto miProducto);

    @PUT("productos/{llave}.json")
    Call<Object> editar(@Path("llave") String id);

    @DELETE("productos/{llave}.json")
    Call<Object> eliminar(@Path("llave") String id);

}
