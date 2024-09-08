package com.example.financierael_transador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.financierael_transador.Modulos.Users;
import com.example.financierael_transador.Servicios.ApiInterface;
import com.example.financierael_transador.Servicios.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearCuenta_Activity extends AppCompatActivity {
    EditText user, password, confirmPassword, email;
    Button buttonCreateUser;
    TextView backLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_cuenta_activity);

        // Asegúrate de que los IDs coincidan con los del layout XML
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        email = findViewById(R.id.email);
        buttonCreateUser = findViewById(R.id.button_CreateUser);
        backLogin = findViewById(R.id.back_login);

        buttonCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrearUsuario();
            }
        });

        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearCuenta_Activity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void CrearUsuario() {
        String name = user.getText().toString();
        String pass = password.getText().toString();
        String confPass = confirmPassword.getText().toString();
        String emailStr = email.getText().toString();

        if (name.isEmpty() || pass.isEmpty() || confPass.isEmpty() || emailStr.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pass.equals(confPass)) {
            Toast.makeText(this, "La contraseña no coincide.", Toast.LENGTH_SHORT).show();
            return;
        }

        Users newUser = new Users();
        newUser.setUsername(name);
        newUser.setPassword(pass);
        newUser.setEmail(emailStr);

        ApiInterface apiService = ApiService.getApiService();
        Call<Users> call = apiService.GuardarUsuario(newUser);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(@NonNull Call<Users> call, @NonNull Response<Users> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Users user = response.body();
                    Toast.makeText(CrearCuenta_Activity.this, "Usuario creado exitosamente.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CrearCuenta_Activity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(CrearCuenta_Activity.this, "Error al crear usuario: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Users> call, @NonNull Throwable t) {
                Toast.makeText(CrearCuenta_Activity.this, "Error en la solicitud: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
