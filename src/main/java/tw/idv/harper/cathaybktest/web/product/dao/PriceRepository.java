package tw.idv.harper.cathaybktest.web.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.idv.harper.cathaybktest.web.product.vo.Price;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p.date FROM Price p WHERE p.productId = :productId")
    List<Long> findDatesByProductId(String productId);

    @Query("SELECT pe.productId, pt.name, pt.shortName, pe.date, pe.price, pe.priceId " +
            "FROM Price pe " +
            "JOIN Product pt ON pe.productId = pt.productId " +
            "WHERE pe.productId = :productId " +
            "AND pe.date = :date")
    List<Object[]> findDatesByProductIdAndDate(String productId, Long date);

    Price findByPriceId(Long priceId);
}


