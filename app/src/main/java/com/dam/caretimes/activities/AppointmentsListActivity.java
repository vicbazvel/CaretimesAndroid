package com.dam.caretimes.activities;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.dam.caretimes.R;
import com.dam.caretimes.models.MedicalAppointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsListActivity extends AppCompatActivity {

    private ListView lvAppointments;
    private List<MedicalAppointment> appointmentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments_list);

        lvAppointments = findViewById(R.id.lvAppointments);

        // Datos de ejemplo
        appointmentsList = new ArrayList<>();
        appointmentsList.add(new MedicalAppointment("Dr. Pérez", "Cardiología", "15/06/2023", "10:00 AM"));
        appointmentsList.add(new MedicalAppointment("Dra. Gómez", "Dermatología", "20/06/2023", "04:30 PM"));

        ArrayAdapter<MedicalAppointment> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, appointmentsList);
        lvAppointments.setAdapter(adapter);

        lvAppointments.setOnItemClickListener((parent, view, position, id) -> {
            MedicalAppointment selectedAppointment = appointmentsList.get(position);
            Toast.makeText(this, "Cita seleccionada: " + selectedAppointment.getDoctorName(), Toast.LENGTH_SHORT).show();
        });
    }
}
