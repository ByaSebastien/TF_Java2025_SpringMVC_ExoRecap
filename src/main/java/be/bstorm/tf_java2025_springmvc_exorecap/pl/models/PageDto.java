package be.bstorm.tf_java2025_springmvc_exorecap.pl.models;

import java.util.List;

public record PageDto<T>(
        List<T> content,
        int totalPages,
        int currentPage
) {
}
