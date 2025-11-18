package org.example;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Projecte {

    private String nomProjecte;
    private LocalDate dataInici;
    private static List<Participa> participacions;
    private List<Tasca> tasques;

    public Projecte(String nomProjecte, LocalDate dataInici){
        this.nomProjecte = nomProjecte;
        this.dataInici = dataInici;
        this.participacions = new ArrayList<>();
        this.tasques = new ArrayList<>();
    }


    public static void afegirParticipacio(Participa participa) {
        participacions.add(participa);
    }

    public void afegirTasca(Tasca tasca) {
        tasques.add(tasca);
    }

    @Override
    public String toString() {
        String resultat = "Projecte: " + nomProjecte + " (Inici: " + dataInici + ")\n";

        resultat += "Participants:\n";
        for (Participa participa : participacions) {
            resultat += "-" + participa + "\n";
        }
        resultat += "Tasques:\n";
        for (Tasca tasca : tasques) {
            resultat += "- " + tasca + "\n";
        }

        return resultat;
    }


}