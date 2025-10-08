package be.bstorm.tf_java2025_springmvc_exorecap.bll.services.specifications;

import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.Product;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class ProductSpecification {

    public static Specification<Product> joinCategory(){
        return ((root, query, criteriaBuilder) -> {
            if (Objects.requireNonNull(query).getResultType() != Long.class) {
                root.fetch("category", JoinType.LEFT);
            }
            return criteriaBuilder.conjunction();
        });
    }
}
