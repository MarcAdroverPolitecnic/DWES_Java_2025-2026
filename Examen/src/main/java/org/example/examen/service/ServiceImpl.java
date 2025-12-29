package org.example.examen.service;

import org.example.examen.dao.Dao;
import org.example.examen.dao.DaoOrmImpl;

public class ServiceImpl implements Service{

    private final Dao gameDao;

    public ServiceImpl() {
        this(new DaoOrmImpl());
    }

    public ServiceImpl(Dao gameDao) {
        this.gameDao = gameDao;
    }
}
