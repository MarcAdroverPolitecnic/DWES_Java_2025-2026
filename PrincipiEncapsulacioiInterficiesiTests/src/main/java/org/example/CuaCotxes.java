package org.example;

import java.util.NoSuchElementException;

public interface CuaCotxes {
    public void encuar (Cotxe cotxe) throws IllegalArgumentException;
    public void desencuar () throws NoSuchElementException;
    public void cancelar () throws NoSuchElementException;
    public Cotxe get(int numCotxe);
    public int count();
}
