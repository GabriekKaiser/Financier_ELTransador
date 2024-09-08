package com.example.financierael_transador.Servicios;

import com.example.financierael_transador.Modulos.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

        @GET("/api/usuarios")
        Call<List<Users>> obtenerTodosUsuarios();

        @GET("/api/usuarios/{username}/{password}")
        Call<Users> buscarUsuario(@Path("username") String username, @Path("password") String password);

        @POST("/api/usuarios")
        Call<Users> guardarUsuario(@Body Users usuario);


}


