package com.lito.fupin.business.organize;

import java.util.Map;

public interface IOrganizeBusinessService {
    Map createOrganize(Map in) throws Exception;

    Map listOrganize() throws Exception;
}
