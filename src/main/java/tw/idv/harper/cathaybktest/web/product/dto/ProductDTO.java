package tw.idv.harper.cathaybktest.web.product.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductDTO {

    private String productId;

    private String name;

    private String shortName;

    private Long date;

    private BigDecimal price;

    private Long priceId;
}
