package com.lito.fupin.business.user;

import com.lito.fupin.meta.organize.entity.Organize;

import java.util.ArrayList;
import java.util.Map;

public interface IUserBusinessService {
    Map register(Map in) throws Exception;

    Map login(Map in) throws Exception;

    Map listUserByToken(Map in) throws Exception;
}
