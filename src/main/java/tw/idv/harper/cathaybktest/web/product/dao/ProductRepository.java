package tw.idv.harper.cathaybktest.web.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.harper.cathaybktest.web.product.vo.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
