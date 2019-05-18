package com.lito.fupin.business.user;

import java.util.Map;

public interface IUserBusinessService {
    Map register(Map in) throws Exception;

    Map login(Map in) throws Exception;
}
