package tw.idv.harper.cathaybktest.web.product.vo;

import lombok.Data;
import tw.idv.harper.cathaybktest.core.pojo.Core;

@Data
public class Price extends Core {
    private String productId;
    private Long date;
    private Double price;

}
