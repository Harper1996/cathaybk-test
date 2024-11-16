package tw.idv.harper.cathaybktest.web.API.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tw.idv.harper.cathaybktest.core.pojo.Core;
import tw.idv.harper.cathaybktest.web.API.service.ApiDataService;

@RestController
@RequestMapping("/api")
public class ApiDataController {

    @Autowired
    private ApiDataService apiDataService;

    @PostMapping()
    public ResponseEntity<Core> fetchAndSaveData(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        Core response = new Core();
        try {
            response =apiDataService.fetchAndSaveData("https://www.cathaybk.com.tw/cathaybk/service/newwealth/fund/chartservice.asmx/GetFundNavChart", startDate, endDate);
        } catch (Exception e) {
            response.setSuccessful(false);
            response.setMessage(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}

