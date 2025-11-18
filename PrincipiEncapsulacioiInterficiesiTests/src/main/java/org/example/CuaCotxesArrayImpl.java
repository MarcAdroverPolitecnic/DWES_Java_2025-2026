package org.example;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class CuaCotxesArrayImpl implements CuaCotxes {

    private Cotxe[] cua = new Cotxe[1];

    private int tamany;

    public void encuar (Cotxe cotxe){
        if(cotxe == null){
            throw new IllegalArgumentException("Cotxe es null");
        }
        if(tamany == cua.length){
            cua = Arrays.copyOf(cua, cua.length + 1);
        }
        cua[tamany] = cotxe;
        tamany++;
    };

    public void desencuar () {
        if(tamany == 0){
            throw new NoSuchElementException("La cua esta buida");
        }
        for (int i = 1; i < tamany; i++) {
            cua[i - 1] = cua[i];
        }
        tamany--;

    };

    public void cancelar (){
        if(tamany == 0){
            throw new NoSuchElementException("La cua esta buida");
        }
        cua[tamany -1] = null;
        tamany--;
    };

    public Cotxe get(int numCotxe){
        if(numCotxe < 0 || numCotxe >= tamany){
            throw new IndexOutOfBoundsException("Posició fora de rang: " + numCotxe);
        }
        return cua[numCotxe];
        };

    public int count(){
        return tamany;
    };
}
