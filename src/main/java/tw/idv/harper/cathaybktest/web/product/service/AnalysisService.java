package tw.idv.harper.cathaybktest.web.product.service;

import tw.idv.harper.cathaybktest.core.pojo.Core;

import java.math.BigDecimal;

public interface AnalysisService {
    Core profit(Long startTime, Long endTime, String productId);
    Core margin(Long startTime, Long endTime, String productId);
}
