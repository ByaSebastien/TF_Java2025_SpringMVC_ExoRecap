package be.bstorm.tf_java2025_springmvc_exorecap.pl.models.dtos.product;

import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
public class ProductForm {

    @NotBlank
    @Size(max = 50)
    private String name;

    @Size(max = 500)
    private String description;

    @Range(min = 0)
    private double price;

    @NotNull
    private Long categoryId;

    private MultipartFile image;

    public ProductForm(String name, String description, double price, Long categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
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

    public static ProductForm fromEntity(Product p) {
        double euros = BigDecimal.valueOf(p.getPrice())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
                .doubleValue();

        return new ProductForm(
                p.getName(),
                p.getDescription(),
                euros,
                p.getCategory().getId()
        );
    }
}
