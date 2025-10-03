package be.bstorm.tf_java2025_springmvc_exorecap.dl.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Entity
@Getter
@NoArgsConstructor
@ToString(callSuper = true, of = {"name", "category"})
@EqualsAndHashCode(callSuper = true, of = {"name"})
public class Product extends BaseEntity<Long>{

    @Setter
    @Column(unique = true,nullable = false,length = 50)
    private String name;

    @Setter
    @Column(nullable = true, length = 500)
    private String description;

    @Setter
    @Column(nullable = false)
    @Range(min = 0)
    private int price;

    @Column(name = "category_id", insertable = false, updatable = false)
    private Long categoryId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "category_id")
    private Category category;
}
