package org.example;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CuaCotxesArrayListImpl implements CuaCotxes{
    private final ArrayList<Cotxe> cua = new ArrayList<Cotxe>();

    @Override
    public void encuar(Cotxe cotxe) {
        if (cotxe == null){
            throw new IllegalArgumentException();
        }
        cua.add(cotxe);
    }

    @Override
    public void desencuar() {
        if (cua.isEmpty()){
            throw new NoSuchElementException();
        }
        cua.removeFirst();
    }

    @Override
    public void cancelar() throws NoSuchElementException {
        if (cua.isEmpty()){
            throw new NoSuchElementException();
        }
        cua.removeLast();
    }

    @Override
    public Cotxe get(int numCotxe) {
        return cua.get(numCotxe);
    }

    @Override
    public int count() {
        return cua.size();
    }
}
