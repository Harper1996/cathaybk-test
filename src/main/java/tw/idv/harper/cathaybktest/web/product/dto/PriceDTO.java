package tw.idv.harper.cathaybktest.web.product.dto;

import lombok.Data;
import tw.idv.harper.cathaybktest.web.product.vo.Price;

import java.util.List;

@Data
public class PriceDTO {
    private String productId;
    private List<Price> data; // 每項包含 [timestamp, price]
}

