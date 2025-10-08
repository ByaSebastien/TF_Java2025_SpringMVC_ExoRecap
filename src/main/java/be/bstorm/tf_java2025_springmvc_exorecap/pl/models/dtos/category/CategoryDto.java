package be.bstorm.tf_java2025_springmvc_exorecap.pl.models.dtos.category;

import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.Category;

public record CategoryDto(
        Long id,
        String name
) {

    public static CategoryDto fromCategory(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}
