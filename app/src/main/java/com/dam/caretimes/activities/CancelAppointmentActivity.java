package com.dam.caretimes.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.dam.caretimes.R;
import com.dam.caretimes.models.MedicalAppointment;

public class CancelAppointmentActivity extends AppCompatActivity {

    private ListView lvCancelAppointments;
    private ArrayAdapter<MedicalAppointment> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_appointment);

        lvCancelAppointments = findViewById(R.id.lvCancelAppointments);

        // Cargamos las citas compartidas
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                AppointmentsListActivity.appointmentsList
        );
        lvCancelAppointments.setAdapter(adapter);

        lvCancelAppointments.setOnItemClickListener((parent, view, position, id) -> {
            MedicalAppointment toCancel = AppointmentsListActivity.appointmentsList.get(position);
            // Confirmación de borrado
            new AlertDialog.Builder(this)
                    .setTitle("Cancelar cita")
                    .setMessage("¿Seguro que deseas cancelar la cita con "
                            + toCancel.getSpecialty()
                            + " el " + toCancel.getDate()
                            + " a las " + toCancel.getTime() + "?")
                    .setPositiveButton("OK", (dialog, which) -> {
                        // 1) Eliminamos de la lista provisional
                        AppointmentsListActivity.appointmentsList.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "Cita cancelada", Toast.LENGTH_SHORT).show();

                        // TODO: llamar al endpoint REST para cancelar la cita en el servidor
                        // ApiClient.getService().deleteAppointment(toCancel.getId())...
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }
}
