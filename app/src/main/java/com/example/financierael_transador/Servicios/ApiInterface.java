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

    @GET("/usuarios/all")
    Call<List<Users>> ObtenertodosUsuarios();


    /*todo lo que tiene que ver con trivia*/

    /*opcion multiple*/


    /*todo lo que tiene que ver con Opciones*/


    @DELETE("opcion/{opcionId}")
    Call<Integer> DeleteOpcion(@Path("opcionId") int id);


    /*todo lo que tiene que ver con intentos */



    /*todo lo que tiene que ver con usuario*/

    @GET("/usuarioss/{usuarioId}")
    Call<Users> BuscarUsuarioById(@Path("usuarioId") int id);

    @GET("/login")
    Call<Users> buscarUsuario(@Query("nombreUsuario") String nombreUsuario, @Query("password") String password);

    @POST("/usuario")
    Call<Users> GuardarUsuario(@Body Users user);

    @DELETE("usuarios/{id}")
    Call<Void> eliminarUsuario(@Path("id") int id);

    @PUT("/usuario/{usuarioId}")
    Call<Users> ModificarUsuario(@Path("usuarioId") int userId, @Body Users user);

}


