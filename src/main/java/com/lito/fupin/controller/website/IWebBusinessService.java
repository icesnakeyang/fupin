package com.lito.fupin.controller.website;

import java.util.Map;

public interface IWebBusinessService {
    Map loadNewsHomePage(Map in) throws Exception;

    Map loadPaperDetailPage(Map in) throws Exception;
}
