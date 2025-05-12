package com.dam.caretimes.models;

public class MedicalAppointment {
    private int id;
    private String doctorName;
    private String specialty;
    private String date;
    private String time;
    private String notes;

    // Constructor, getters y setters
    public MedicalAppointment(String doctorName, String specialty, String date, String time) {
        this.doctorName = doctorName;
        this.specialty = specialty;
        this.date = date;
        this.time = time;
    }

    // Getters y setters...
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    // Getter y setter para el campo notes
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Metodo toString para mostrar en la lista
    @Override
    public String toString() {
        return doctorName + " - " + specialty + " (" + date + " " + time + ")";
    }
}
