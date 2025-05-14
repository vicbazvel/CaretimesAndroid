package com.dam.caretimes.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.dam.caretimes.R;
import com.dam.caretimes.models.MedicalAppointment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class NewAppointmentActivity extends AppCompatActivity {

    private EditText etDoctorName, etSpecialty, etDate, etTime, etNotes;
    private Button btnSaveAppointment;

    // Definimos 5 especialidades de ejemplo
    private final String[] specialties = {
            "Cardiología",
            "Dermatología",
            "Pediatría",
            "Neurología",
            "Oftalmología"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);

        // Referencias a views
        etDoctorName       = findViewById(R.id.etDoctorName);
        etSpecialty        = findViewById(R.id.etSpecialty);
        etDate             = findViewById(R.id.etDate);
        etTime             = findViewById(R.id.etTime);
        etNotes            = findViewById(R.id.etNotes);
        btnSaveAppointment = findViewById(R.id.btnSaveAppointment);

        // 1) Ocultar el campo "Doctor"
        etDoctorName.setVisibility(EditText.GONE);

        // 2) Selector de especialidad desde lista
        etSpecialty.setFocusable(false);
        etSpecialty.setClickable(true);
        etSpecialty.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Seleccione especialidad")
                    .setItems(specialties, (dialog, which) -> {
                        etSpecialty.setText(specialties[which]);
                    })
                    .show();
        });

        // 3) DatePicker para fecha
        etDate.setFocusable(false);
        etDate.setClickable(true);
        etDate.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            new DatePickerDialog(
                    NewAppointmentActivity.this,
                    (view, year, month, dayOfMonth) -> {
                        Calendar sel = Calendar.getInstance();
                        sel.set(year, month, dayOfMonth);
                        String formatted = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                                .format(sel.getTime());
                        etDate.setText(formatted);
                    },
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)
            ).show();
        });

        // 4) Selector de hora en medias horas
        etTime.setFocusable(false);
        etTime.setClickable(true);
        etTime.setOnClickListener(v -> {
            String[] slots = generateTimeSlots();
            new AlertDialog.Builder(this)
                    .setTitle("Seleccione hora")
                    .setItems(slots, (dialog, which) -> {
                        etTime.setText(slots[which]);
                    })
                    .show();
        });

        // 5) Guardar cita
        btnSaveAppointment.setOnClickListener(v -> {
            String doctorName = "Dr. Ejemplo";  // placeholder hasta REST
            String specialty  = etSpecialty.getText().toString().trim();
            String date       = etDate.getText().toString().trim();
            String time       = etTime.getText().toString().trim();
            String notes      = etNotes.getText().toString().trim();

            if (specialty.isEmpty() || date.isEmpty() || time.isEmpty()) {
                Toast.makeText(this,
                        "Por favor seleccione especialidad, fecha y hora",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            MedicalAppointment newAppointment =
                    new MedicalAppointment(doctorName, specialty, date, time);
            newAppointment.setNotes(notes);

            // Añadir a la lista compartida para que AppointmentsListActivity lo muestre
            AppointmentsListActivity.appointmentsList.add(newAppointment);

            // TODO: aquí irá la llamada REST para enviar la cita al servidor
            //     ApiClient.getService().createAppointment(newAppointment)...

            Toast.makeText(this,
                    "Cita guardada exitosamente (provisional)",
                    Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    /**
     * Genera un array de horas desde 08:00 a 17:30 en incrementos de 30 minutos.
     */
    private String[] generateTimeSlots() {
        List<String> list = new ArrayList<>();
        for (int hour = 8; hour <= 17; hour++) {
            list.add(String.format(Locale.getDefault(), "%02d:00", hour));
            list.add(String.format(Locale.getDefault(), "%02d:30", hour));
        }
        if (!list.get(list.size()-1).equals("17:30")) {
            list.remove(list.size()-1);
        }
        return list.toArray(new String[0]);
    }
}
