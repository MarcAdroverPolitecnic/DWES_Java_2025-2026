package org.example;

public class Docent extends Persona implements Remunerat{

    private int SSN;

    public Docent(String nom, String DNI, int salari, int SSN) {
        super(nom, DNI, salari);
        this.SSN = SSN;
    }


    @Override
    public int canviarRemuneracio(int remuneracio) {
        return 0;
    }
}
