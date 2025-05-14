package com.dam.caretimes.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.dam.caretimes.R;
import com.dam.caretimes.models.MedicalAppointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsListActivity extends AppCompatActivity {

    private ListView lvAppointments;
    // Lista compartida que irá llenándose desde NewAppointmentActivity
    // TODO: en el futuro cargar aquí las citas desde el servidor REST
    public static List<MedicalAppointment> appointmentsList = new ArrayList<>();

    private ArrayAdapter<MedicalAppointment> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments_list);

        lvAppointments = findViewById(R.id.lvAppointments);

        // Si fuera la primera vez, podrías precargar datos de ejemplo
        if (appointmentsList.isEmpty()) {
            // TODO: reemplazar por llamada REST para obtener citas reales
            appointmentsList.add(new MedicalAppointment("Dr. Pérez", "Cardiología", "15/06/2023", "10:00 AM"));
            appointmentsList.add(new MedicalAppointment("Dra. Gómez", "Dermatología", "20/06/2023", "04:30 PM"));
        }

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                appointmentsList);
        lvAppointments.setAdapter(adapter);

        lvAppointments.setOnItemClickListener((parent, view, position, id) -> {
            MedicalAppointment selected = appointmentsList.get(position);
            Toast.makeText(this,
                    "Cita seleccionada: " + selected.getSpecialty() + " (" + selected.getDate() + " " + selected.getTime() + ")",
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Si NewAppointmentActivity añadió citas a appointmentsList, actualizamos la vista
        adapter.notifyDataSetChanged();
    }
}
