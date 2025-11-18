package org.example;

public class Tasca {

    private String nom;
    private int estimacioHores;
    private Tasca tascaPare;

    public Tasca(int estimacioHores, String nom, Tasca tascaPare) {
        this.estimacioHores = estimacioHores;
        this.nom = nom;
        this.tascaPare = tascaPare;
    }

    public Tasca(int estimacioHores, String nom){
        this.estimacioHores = estimacioHores;
        this.nom = nom;
    }

    public String getNom(){
        return nom;
    }

    @Override
    public String toString() {
        if (tascaPare != null) {
            return nom + " (" + estimacioHores + "h, sub-tasca de " + this.tascaPare.getNom() + ")";
        } else {
            return nom + " (" + estimacioHores + "h)";
        }
    }


}

