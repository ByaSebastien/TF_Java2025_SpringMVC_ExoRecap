package be.bstorm.tf_java2025_springmvc_exorecap.pl.models.dtos.product;

import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.Product;
import be.bstorm.tf_java2025_springmvc_exorecap.pl.models.dtos.category.CategoryDto;

public record ProductDetailDto(
        Long id,
        String name,
        String description,
        String image,
        CategoryDto category
) {

    public static ProductDetailDto fromEntity(Product product) {
        return new ProductDetailDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getImage(),
                CategoryDto.fromCategory(product.getCategory())
        );
    }
}
