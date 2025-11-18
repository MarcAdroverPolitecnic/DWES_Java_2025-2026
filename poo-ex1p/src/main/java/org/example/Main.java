package org.example;
import java.time.LocalDate;

import static org.example.Rol.*;

public class Main{

    public static void main(String[] args) {

        Desenvolupador desenvolupador1 = new Desenvolupador("Marc");
        Desenvolupador desenvolupador2 = new Desenvolupador("Lukas");
        Desenvolupador desenvolupador3 = new Desenvolupador("Toni");

        Tasca tasca1 = new Tasca(3, "Projecte Java");
        Tasca tasca2 = new Tasca(25, "JavaScript", tasca1);
        Tasca tasca3 = new Tasca(1, "Empresa");

        LocalDate dataInici = LocalDate.now();

        Projecte projecte1 = new Projecte("Projecte Pantalla", dataInici);
        Projecte projecte2 = new Projecte("Proecte Placa", dataInici);

        Participa participacio1 = new Participa(projecte1, desenvolupador1, 3, FRONTEND);
        Participa participacio2 = new Participa(projecte2, desenvolupador2, 25, BACKEND);
        Participa participacio3 = new Participa(projecte2, desenvolupador3, 15, FULLSTACK);

        Projecte.afegirParticipacio(participacio1);
        Projecte.afegirParticipacio(participacio2);
        Projecte.afegirParticipacio(participacio3);

        projecte1.afegirTasca(tasca1);
        projecte1.afegirTasca(tasca3);
        projecte2.afegirTasca(tasca2);

        System.out.println(projecte1);

        //Fa falta caviar el model ja que no esta actualitzat (falta per posar els mètodes que falten
        //a cada classe)


    }
}