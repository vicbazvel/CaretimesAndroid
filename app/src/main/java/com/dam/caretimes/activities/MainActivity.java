package com.dam.caretimes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dam.caretimes.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = getIntent().getStringExtra("username");
        TextView tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText("Bienvenido, " + username);

        Button btnViewAppointments = findViewById(R.id.btnViewAppointments);
        Button btnNewAppointment = findViewById(R.id.btnNewAppointment);
        Button btnCancelAppointment = findViewById(R.id.btnCancelAppointment);

        btnViewAppointments.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AppointmentsListActivity.class));
        });

        btnNewAppointment.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, NewAppointmentActivity.class));
        });

        btnCancelAppointment.setOnClickListener(v -> {
            // Implementar lógica para cancelar citas
            Toast.makeText(this, "Seleccione una cita para cancelar", Toast.LENGTH_SHORT).show();
        });
    }
}