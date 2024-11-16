package tw.idv.harper.cathaybktest.web.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tw.idv.harper.cathaybktest.web.product.vo.Price;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p.date FROM Price p WHERE p.productId = :productId")
    List<Long> findDatesByProductId(@Param("productId") String productId);
}

