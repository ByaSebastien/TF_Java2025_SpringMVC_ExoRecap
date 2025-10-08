package be.bstorm.tf_java2025_springmvc_exorecap.bll.services.impls;

import be.bstorm.tf_java2025_springmvc_exorecap.bll.services.CategoryService;
import be.bstorm.tf_java2025_springmvc_exorecap.dal.repositories.CategoryRepository;
import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceimpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
