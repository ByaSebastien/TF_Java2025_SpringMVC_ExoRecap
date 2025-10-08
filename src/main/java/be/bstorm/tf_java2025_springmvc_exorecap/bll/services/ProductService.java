package be.bstorm.tf_java2025_springmvc_exorecap.bll.services;

import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.Product;
import be.bstorm.tf_java2025_springmvc_exorecap.il.utils.request.SearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    Page<Product> getProducts(List<SearchParam<Product>> searchParams, Pageable pageable);
    Product getProduct(Long id);
    void addProduct(Product product, MultipartFile image);
    void editProduct(Long id, Product product, MultipartFile image);
    void deleteProduct(Long id);
}
