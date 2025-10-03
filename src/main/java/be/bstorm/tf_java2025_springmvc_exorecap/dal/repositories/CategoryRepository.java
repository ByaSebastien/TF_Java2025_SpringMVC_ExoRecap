package be.bstorm.tf_java2025_springmvc_exorecap.dal.repositories;

import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
