package tw.idv.harper.cathaybktest.web.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.idv.harper.cathaybktest.core.pojo.Core;
import tw.idv.harper.cathaybktest.web.product.dao.PriceRepository;
import tw.idv.harper.cathaybktest.web.product.dao.ProductRepository;
import tw.idv.harper.cathaybktest.web.product.dto.PriceDTO;
import tw.idv.harper.cathaybktest.web.product.service.PriceService;
import tw.idv.harper.cathaybktest.web.product.vo.Price;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PriceRepository priceRepository;


    @Transactional
    public Core addPrices(PriceDTO priceDTO) {
        Core response = new Core();
        response.setSuccessful(false);

        // 檢查商品是否存在
        if (!productRepository.existsById(priceDTO.getProductId())) {
            response.setMessage("產品錯誤");
            return response;

        }

        //檢查輸入是否為空
        if(priceDTO.getData().isEmpty()){
            response.setMessage("輸入錯誤");
            return response;
        }

        // 獲取資料庫中已存在的日期集合
        List<Long> existingDates = priceRepository.findDatesByProductId(priceDTO.getProductId());

        // 檢查是否有重複的日期
        boolean hasDuplicate = priceDTO.getData().stream()
                .anyMatch(price -> existingDates.contains(price.getDate()));

        if (hasDuplicate) {
            response.setMessage("該日期已輸入價格");
            return response;
        }


        // 將價格陣列轉換為 priceList 並儲存
        List<Price> priceList = priceDTO.getData().stream().map(price -> {
            price.setProductId(priceDTO.getProductId()); // 補充 productId
            return price;
        }).collect(Collectors.toList());

        for (Price p:priceList){
            if (p.getDate() == null){
                response.setMessage("日期錯誤");
                return response;
            }
            if (p.getPrice() == null){
                response.setMessage("價格錯誤");
                return response;
            }
        }

        priceRepository.saveAll(priceList);
        response.setSuccessful(true);
        response.setMessage("加入成功");
        return response;
    }
}