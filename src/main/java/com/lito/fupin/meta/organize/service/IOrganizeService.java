package com.lito.fupin.meta.organize.service;

import com.lito.fupin.meta.organize.entity.Organize;

import java.util.ArrayList;
import java.util.Map;

public interface IOrganizeService {
    void createOrganize(Organize organize) throws Exception;

    ArrayList<Organize> listOrganize() throws Exception;
}
