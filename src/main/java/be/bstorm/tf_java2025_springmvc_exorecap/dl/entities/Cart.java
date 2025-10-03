package be.bstorm.tf_java2025_springmvc_exorecap.dl.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@ToString(callSuper = true,of = {"userId"})
@EqualsAndHashCode(callSuper = true, of = {"userId"})
public class Cart extends BaseEntity<Long>{

    @Getter
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @Getter @Setter
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;
}
