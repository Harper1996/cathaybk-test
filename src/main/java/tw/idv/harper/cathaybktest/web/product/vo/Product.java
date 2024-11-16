package tw.idv.harper.cathaybktest.web.product.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import tw.idv.harper.cathaybktest.core.pojo.Core;

@Data
@Entity
public class Product extends Core {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String productId;
    private String name;
    private String shortName;
    private Boolean dataGrouping;
}
