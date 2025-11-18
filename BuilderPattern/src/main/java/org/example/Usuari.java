package org.example;

/*public class Usuari {
    private String nom;
    private String llinatge;
    private int edat;
    private String email;
    private String telefon;
    private String direccio;

    public Usuari(String nom, String llinatge, int edat, String email, String telefon, String direccio) {
        this.nom = nom;
        this.llinatge = llinatge;
        this.edat = edat;
        this.email = email;
        this.telefon = telefon;
        this.direccio = direccio;
    }
}
*/

public class Usuari {
    private String nom;
    private String llinatge;
    private int edat;
    private String email;
    private String telefon;
    private String direccio;

    private Usuari(Builder builder) {
        this.nom = builder.nom;
        this.llinatge = builder.llinatge;
        this.edat = builder.edat;
        this.email = builder.email;
        this.telefon = builder.telefon;
        this.direccio = builder.direccio;
    }

    // Clase Builder interna
    public static class Builder {
        private String nom;
        private String llinatge;
        private int edat;
        private String email;
        private String telefon;
        private String direccio;

        public Builder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public Builder llinatge(String llinatge) {
            this.llinatge = llinatge;
            return this;
        }

        public Builder edat(int edat) {
            this.edat = edat;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder telefon(String telefon) {
            this.telefon = telefon;
            return this;
        }

        public Builder direccio(String direccio) {
            this.direccio = direccio;
            return this;
        }

        public Usuari build() {
            return new Usuari(this);
        }
    }
}
