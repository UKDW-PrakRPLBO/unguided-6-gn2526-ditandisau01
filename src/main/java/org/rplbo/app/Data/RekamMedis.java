package org.rplbo.app.Data;

public class RekamMedis {
    // Attribute
    private String namaPasien;
    private String diagnosis;
    private String tanggal;
    private int id;
    private String namaDokter;

    // Constructor

    public RekamMedis(String namaPasien, String diagnosis, String tanggal, int id, String namaDokter) {
        this.namaPasien = namaPasien;
        this.diagnosis = diagnosis;
        this.tanggal = tanggal;
        this.id = id;
        this.namaDokter = namaDokter;
    }


    // Getter

    public String getNamaPasien() {
        return namaPasien;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTanggal() {
        return tanggal;
    }

    public int getId() {
        return id;
    }

    public String getNamaDokter() {
        return namaDokter;
    }
}