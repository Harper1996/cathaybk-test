package tw.idv.harper.cathaybktest.web.API.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import tw.idv.harper.cathaybktest.core.pojo.Core;
import tw.idv.harper.cathaybktest.web.API.service.ApiDataService;
import tw.idv.harper.cathaybktest.web.product.dao.PriceRepository;
import tw.idv.harper.cathaybktest.web.product.dao.ProductRepository;
import tw.idv.harper.cathaybktest.web.product.vo.Price;
import tw.idv.harper.cathaybktest.web.product.vo.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class ApiDataServiceImpl implements ApiDataService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Override
    @Transactional
    public Core fetchAndSaveData(String apiUrl, String startDate, String endDate) {
        Core core = new Core();
        core.setSuccessful(false);

        // 構建請求參數
        Map<String, Object> requestBody = Map.of(
                "req", Map.of(
                        "keys", List.of("10480016"), // 需要的商品 ID 列表
                        "From", startDate,
                        "To", endDate
                )
        );

        // 發送 HTTP POST 請求
        ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, requestBody, Map.class);

        // 檢查響應結果
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            core.setMessage("API 請求失敗或返回空結果");
            return core;
        }

        // 解析商品與價格數據
        List<Map<String, Object>> products = (List<Map<String, Object>>) response.getBody().get("Data");
        for (Map<String, Object> productData : products) {
            core = saveProductAndPrices(productData);
        }
        return core;

    }

    private Core saveProductAndPrices(Map<String, Object> productData) {
        Core core = new Core();

        // 保存商品資料
        String productId = (String) productData.get("id");
        if (!productRepository.existsById(productId)) {
            Product product = new Product();
            product.setProductId(productId);
            product.setName((String) productData.get("name"));
            product.setShortName((String) productData.get("shortName"));
            product.setDataGrouping((Boolean) productData.get("dataGrouping"));
            productRepository.save(product);
        }

        // 保存價格資料
        List<List<Object>> priceDataList = (List<List<Object>>) productData.get("data");
        Boolean flag = false;
        for (List<Object> priceData : priceDataList) {
            Long date = ((Number) priceData.get(0)).longValue();
            BigDecimal price = new BigDecimal(priceData.get(1).toString());

            // 檢查是否已存在
            if (!priceRepository.existsByProductIdAndDate(productId, date)) {
                Price priceEntity = new Price();
                priceEntity.setProductId(productId);
                priceEntity.setDate(date);
                priceEntity.setPrice(price);
                priceRepository.save(priceEntity);
                flag = true;
            }
        }
        if(flag){
            core.setMessage("數據抓取並保存成功");
            core.setSuccessful(true);
        }else {
            core.setMessage("請勿重複添加資料");
            core.setSuccessful(false);
        }

        return core;
    }
}