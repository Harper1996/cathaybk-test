package tw.idv.harper.cathaybktest.web.API.service;

import tw.idv.harper.cathaybktest.core.pojo.Core;

public interface ApiDataService {

    Core fetchAndSaveData(String apiUrl, String startDate, String endDate);
}
