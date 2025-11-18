package org.example;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class CuaCotxesQueueImpl implements CuaCotxes{
    private Queue<Cotxe> cua = new LinkedList<Cotxe>();

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
        cua.remove();
    }

    @Override
    public void cancelar() throws NoSuchElementException {
        if (cua.isEmpty()){
            throw new NoSuchElementException();
        }
        Queue<Cotxe> cua2 = new LinkedList<Cotxe>();
        while (cua.size() != 1){
            cua2.add(cua.remove());
        }
        cua = cua2;
    }

    @Override
    public Cotxe get(int numCotxe) {
        Queue<Cotxe> cua2 = new LinkedList<>();
        cua2.addAll(cua);
        if (numCotxe == 0){
            return cua2.remove();
        }
        while (numCotxe != 0){
            cua2.remove();
            numCotxe--;
        }
        return cua2.remove();
    }

    @Override
    public int count() {
        return cua.size();
    }
}
