package be.bstorm.tf_java2025_springmvc_exorecap.dal.repositories;

import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    boolean existsByName(String name);

    @Query("select p from Product p join p.category c where p.id = :id")
    Optional<Product> findByIdWithCategory(Long id);
}
