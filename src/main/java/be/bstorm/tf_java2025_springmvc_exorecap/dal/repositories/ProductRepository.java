package be.bstorm.tf_java2025_springmvc_exorecap.dal.repositories;

import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
