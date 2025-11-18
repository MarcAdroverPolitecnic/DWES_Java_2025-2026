package org.example;

public class Becari extends Alumne implements Remunerat{

    private int salari;

    public Becari(String nom, String DNI, int salari) {
        super(nom, DNI);
        this.salari = salari;
    }

    @Override
    public int canviarRemuneracio(int remuneracio) {
        return 0;
    }
}
