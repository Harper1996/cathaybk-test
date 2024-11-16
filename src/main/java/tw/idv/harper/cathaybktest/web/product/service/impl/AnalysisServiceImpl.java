package tw.idv.harper.cathaybktest.web.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.idv.harper.cathaybktest.core.pojo.Core;
import tw.idv.harper.cathaybktest.web.product.dao.PriceRepository;
import tw.idv.harper.cathaybktest.web.product.service.AnalysisService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public Core profit(Long startTime, Long endTime, String productId) {
        Core response = new Core();
        response.setSuccessful(false);

        // 檢查時間區間
        if (endTime == null || startTime == null || endTime <= startTime) {
            response.setMessage("結束時間必須大於開始時間");
            return response;
        }
        // 獲取開始時間的價格
        BigDecimal startPrice = priceRepository.findPriceByProductIdAndDate(productId, startTime);

        // 獲取結束時間的價格
        BigDecimal endPrice = priceRepository.findPriceByProductIdAndDate(productId, endTime);

        // 若價格不存在，返回 null 或拋出異常
        if (startPrice == null || endPrice == null) {
            response.setMessage("無法找到開始時間或結束時間的價格");
            return response;
        }

        // 計算漲跌
        BigDecimal subtract = endPrice.subtract(startPrice);

        response.setSuccessful(true);
        response.setMessage("成功");
        response.setData(subtract);
        return response;

    }

    @Override
    public Core margin(Long startTime, Long endTime, String productId) {
        Core response = new Core();
        response.setSuccessful(false);

        // 檢查時間區間
        if (endTime == null || startTime == null || endTime <= startTime) {
            response.setMessage("結束時間必須大於開始時間");
            return response;
        }

        // 獲取開始時間的價格
        BigDecimal startPrice = priceRepository.findPriceByProductIdAndDate(productId, startTime);

        // 獲取結束時間的價格
        BigDecimal endPrice = priceRepository.findPriceByProductIdAndDate(productId, endTime);

        // 若價格不存在，返回 null 或拋出異常
        if (startPrice == null || endPrice == null || startPrice.compareTo(BigDecimal.ZERO) == 0) {
            response.setMessage("無法找到開始時間或結束時間的價格，或開始價格為零");
            return response;
        }

        // 計算漲跌幅
        BigDecimal divide = endPrice.subtract(startPrice).divide(startPrice, 5, RoundingMode.HALF_UP);

        response.setSuccessful(true);
        response.setMessage("成功");
        response.setData(divide);
        return response;
    }
}
