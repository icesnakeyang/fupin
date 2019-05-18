package com.lito.fupin.meta.organize.service;

import com.lito.fupin.meta.organize.dao.OrganizeDao;
import com.lito.fupin.meta.organize.entity.Organize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class OrganizeService implements IOrganizeService{
    private final OrganizeDao organizeDao;

    @Autowired
    public OrganizeService(OrganizeDao organizeDao) {
        this.organizeDao = organizeDao;
    }

    @Override
    public void createOrganize(Organize organize) throws Exception {
        organizeDao.createOrganize(organize);
    }

    @Override
    public ArrayList<Organize> listOrganize() throws Exception {
        ArrayList<Organize> organizes=organizeDao.listOrganize();
        return organizes;
    }
}
