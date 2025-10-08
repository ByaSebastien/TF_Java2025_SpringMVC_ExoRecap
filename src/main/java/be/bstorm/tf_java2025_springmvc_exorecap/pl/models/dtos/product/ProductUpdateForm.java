package be.bstorm.tf_java2025_springmvc_exorecap.pl.models.dtos.product;

import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
public class ProductUpdateForm {

    @NotBlank
    @Size(max = 50)
    private String name;

    @Size(max = 500)
    private String description;

    @Range(min = 0)
    private double price;

    @NotNull
    private Long categoryId;

    private String imageUrl;

    private MultipartFile image;

    public ProductUpdateForm(String name, String description, double price, Long categoryId, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
    }

    public Product ToEntity() {
        int cents = BigDecimal.valueOf(price)
                .multiply(BigDecimal.valueOf(100))
                .setScale(0, RoundingMode.HALF_UP)
                .intValueExact();

        return new Product(
                name,
                description,
                cents,
                categoryId
        );
    }

    public static ProductUpdateForm fromEntity(Product p) {
        double euros = BigDecimal.valueOf(p.getPrice())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
                .doubleValue();

        return new ProductUpdateForm(
                p.getName(),
                p.getDescription(),
                euros,
                p.getCategory().getId(),
                p.getImage()
        );
    }
}