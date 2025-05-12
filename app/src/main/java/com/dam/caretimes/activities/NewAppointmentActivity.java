package com.dam.caretimes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dam.caretimes.R;
import com.dam.caretimes.models.MedicalAppointment;

public class NewAppointmentActivity extends AppCompatActivity {

    private EditText etDoctorName, etSpecialty, etDate, etTime, etNotes;
    private Button btnSaveAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);

        etDoctorName = findViewById(R.id.etDoctorName);
        etSpecialty = findViewById(R.id.etSpecialty);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        etNotes = findViewById(R.id.etNotes);
        btnSaveAppointment = findViewById(R.id.btnSaveAppointment);

        btnSaveAppointment.setOnClickListener(v -> {
            String doctorName = etDoctorName.getText().toString();
            String specialty = etSpecialty.getText().toString();
            String date = etDate.getText().toString();
            String time = etTime.getText().toString();
            String notes = etNotes.getText().toString();

            if (doctorName.isEmpty() || specialty.isEmpty() || date.isEmpty() || time.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            MedicalAppointment newAppointment = new MedicalAppointment(doctorName, specialty, date, time);
            newAppointment.setNotes(notes);

            // Aquí deberías guardar en la base de datos
            Toast.makeText(this, "Cita guardada exitosamente", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}