package org.example;

public class Main {
    public static void main(String[] args) {


        /*Usuari u = new Usuari("Juan", "Pérez", 30, "juan@gmail.com",
                null, null);
*/


        Usuari usuari = new Usuari.Builder()
                .nom("Juan")
                .llinatge("Pérez")
                .edat(30)
                .email("juan@correo.com")
                .build();


        /*El patró builder, aixió com el Singleton, Factory i Prototype varen ser
        creats per la Gang Of Four:


            -Erich Gamma

            -Richard Helm

            -Ralph Johnson

            -John Vlissides

            Design Patterns: Elements of Reusable Object-Oriented Software


        Un patró de disseny és una solució general, reutilitzable i provada per
        resoldre un problema comú de disseny de programari dins un context específic.

*/

    }
}

