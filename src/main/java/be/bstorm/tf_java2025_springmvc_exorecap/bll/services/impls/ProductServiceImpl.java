package be.bstorm.tf_java2025_springmvc_exorecap.bll.services.impls;

import be.bstorm.tf_java2025_springmvc_exorecap.bll.services.ProductService;
import be.bstorm.tf_java2025_springmvc_exorecap.bll.services.specifications.ProductSpecification;
import be.bstorm.tf_java2025_springmvc_exorecap.dal.repositories.CategoryRepository;
import be.bstorm.tf_java2025_springmvc_exorecap.dal.repositories.ProductRepository;
import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.Category;
import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.Product;
import be.bstorm.tf_java2025_springmvc_exorecap.il.utils.files.FileUtil;
import be.bstorm.tf_java2025_springmvc_exorecap.il.utils.request.SearchParam;
import be.bstorm.tf_java2025_springmvc_exorecap.il.utils.specifications.SearchSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Override
    public Page<Product> getProducts(List<SearchParam<Product>> searchParams, Pageable pageable) {
        List<Specification<Product>> spec = searchParams.stream()
                .map(SearchSpecification::search)
                .toList();

        return productRepository.findAll(
                Specification
                        .allOf(spec)
                        .and(ProductSpecification.joinCategory()),
                pageable
        );
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findByIdWithCategory(id).orElseThrow();
    }

    @Override
    public void addProduct(Product product, MultipartFile image) {

        if (productRepository.existsByName(product.getName())) {

            throw new RuntimeException("Product with name " + product.getName() + " already exists");
        }

        Category category = categoryRepository.findById(product.getCategoryId()).orElseThrow();

        product.setCategory(category);

        if (image != null && !image.isEmpty()) {

            String imageUrl = fileUtil.save(image);
            product.setImage(imageUrl);
        }

        productRepository.save(product);
    }

    @Override
    public void editProduct(Long id, Product product, MultipartFile image) {
        Product existing =  productRepository.findByIdWithCategory(id).orElseThrow();

        if (!existing.getName().equals(product.getName()) && productRepository.existsByName(product.getName())) {
            throw new RuntimeException("Product with name " + product.getName() + " already exists");
        }

        Category category = categoryRepository.findById(product.getCategoryId()).orElseThrow();

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setCategory(category);

        if (image != null && !image.isEmpty()) {

            String imageUrl = fileUtil.save(image);
            existing.setImage(imageUrl);
        }

        productRepository.save(existing);
    }

    @Override
    public void deleteProduct(Long id) {

        if(!productRepository.existsById(id)){
            throw new RuntimeException("Product with id " + id + " does not exist");
        }

        productRepository.deleteById(id);
    }
}
