package tw.idv.harper.cathaybktest.web.product.vo;

import lombok.Data;
import tw.idv.harper.cathaybktest.core.pojo.Core;

@Data
public class Product extends Core {
    private String id;
    private String name;
    private String shortName;
    private Boolean dataGrouping;
}
