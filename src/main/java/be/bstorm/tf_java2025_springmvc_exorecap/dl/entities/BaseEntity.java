package be.bstorm.tf_java2025_springmvc_exorecap.dl.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
@Getter
@MappedSuperclass
public class BaseEntity<T extends Serializable> {

    @Id
    @GeneratedValue
    private T id;

    @Column(name = "CREATED_AT",nullable = false,updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT",nullable = false,updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}