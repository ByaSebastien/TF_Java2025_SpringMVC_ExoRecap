package be.bstorm.tf_java2025_springmvc_exorecap.dl.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Entity
@Getter
@NoArgsConstructor
@ToString(callSuper = true, of = {"productId", "quantity"})
@EqualsAndHashCode(callSuper = true, of = {"productId"})
public class CartLine extends BaseEntity<Long>{

    @Setter
    @Column(nullable = false)
    @Range(min = 1)
    private int quantity;

    @Column(name = "cart_id", insertable = false, updatable = false)
    private Long cartId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Long productId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    private Product product;
}
