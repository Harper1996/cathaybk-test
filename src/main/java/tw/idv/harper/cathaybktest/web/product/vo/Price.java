package tw.idv.harper.cathaybktest.web.product.vo;

import jakarta.persistence.*;
import lombok.Data;
import tw.idv.harper.cathaybktest.core.pojo.Core;

import java.math.BigDecimal;

@Data
@Entity
public class Price extends Core {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priceId;

    private String productId;

    private Long date;

    @Column(name = "price", precision = 10, scale = 5)
    private BigDecimal price;



}
