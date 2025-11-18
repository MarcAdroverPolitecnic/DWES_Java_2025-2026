package org.example;

public class Participa {

    private Rol rol;
    private int horesAssignades;
    private Projecte projecte;
    private Desenvolupador desenvolupador;

    public Participa(Projecte projecte, Desenvolupador desenvolupador, int horesAssignades, Rol rol){
        this.projecte  = projecte;
        this.desenvolupador = desenvolupador;
        this.horesAssignades = horesAssignades;
        this.rol = rol;

        Projecte.afegirParticipacio(this);
    }

    @Override
    public String toString() {
        return desenvolupador + " --> " + rol + " (" + horesAssignades + "h)";
    }

    public Projecte getProjecte() {
        return projecte;
    }
}
