package be.bstorm.tf_java2025_springmvc_exorecap.pl.models.dtos.product;

import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public record ProductIndexDto(
        Long id,
        String name,
        BigDecimal price,
        String category
) {

    public static ProductIndexDto fromEntity(Product p) {

        BigDecimal euros = BigDecimal.valueOf(p.getPrice())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        return new ProductIndexDto(
                p.getId(),
                p.getName(),
                euros,
                p.getCategory().getName()
        );
    }

    public String getFormattedPrice() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        return format.format(price);
    }
}
