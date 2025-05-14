package com.dam.caretimes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.dam.caretimes.R;
import com.dam.caretimes.models.User;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText etName, etUsername, etPassword;
    private Button btnLogin, btnRegister;
    private boolean isRegisterMode = false;

    // Lista provisional de usuarios (se sustituirá por REST)
    private static final List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etName     = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin   = findViewById(R.id.btnLogin);
        btnRegister= findViewById(R.id.btnRegister);

        // Modo inicial = login
        updateMode();

        btnLogin.setOnClickListener(v -> {
            if (isRegisterMode) {
                // Cambiar a modo login
                isRegisterMode = false;
                updateMode();
                return;
            }
            // Intentar login
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if (validateLogin(username, password)) {
                // TODO REST: validar credenciales en servidor
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(v -> {
            if (!isRegisterMode) {
                // Cambiar a modo registro
                isRegisterMode = true;
                updateMode();
                return;
            }
            // Procesar registro
            String name     = etName.getText().toString().trim();
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos para registrarse", Toast.LENGTH_SHORT).show();
                return;
            }
            // Comprobar usuario no existe
            for (User u : users) {
                if (u.getUsername().equals(username)) {
                    Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            // Añadir a lista provisional
            User newUser = new User(username, password, name);
            users.add(newUser);
            // TODO REST: llamada al servidor para crear usuario
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

            // Volver a modo login
            isRegisterMode = false;
            updateMode();
        });
    }

    private void updateMode() {
        if (isRegisterMode) {
            etName.setVisibility(EditText.VISIBLE);
            btnLogin.setText("Volver a Iniciar");
            btnRegister.setText("Confirmar Registro");
        } else {
            etName.setVisibility(EditText.GONE);
            btnLogin.setText("Iniciar sesión");
            btnRegister.setText("Registrarse");
        }
    }

    private boolean validateLogin(String username, String password) {
        // Busca en lista provisional
        for (User u : users) {
            if (u.getUsername().equals(username) &&
                    u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
