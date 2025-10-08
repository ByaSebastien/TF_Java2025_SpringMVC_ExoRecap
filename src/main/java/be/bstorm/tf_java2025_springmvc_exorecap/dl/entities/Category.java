package be.bstorm.tf_java2025_springmvc_exorecap.dl.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity<Long> {

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
