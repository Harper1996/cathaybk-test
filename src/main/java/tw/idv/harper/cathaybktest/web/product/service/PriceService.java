package tw.idv.harper.cathaybktest.web.product.service;

import tw.idv.harper.cathaybktest.core.pojo.Core;
import tw.idv.harper.cathaybktest.web.product.dao.PriceRepository;
import tw.idv.harper.cathaybktest.web.product.dto.PriceDTO;
import tw.idv.harper.cathaybktest.web.product.vo.Price;

import java.math.BigDecimal;

public interface PriceService {

    Core addPrices(PriceDTO priceDTO);

    Core searchPrice(String productId, Long date);
}
