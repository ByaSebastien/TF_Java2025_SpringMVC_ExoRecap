package be.bstorm.tf_java2025_springmvc_exorecap.pl.controllers;

import be.bstorm.tf_java2025_springmvc_exorecap.bll.services.CategoryService;
import be.bstorm.tf_java2025_springmvc_exorecap.bll.services.ProductService;
import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.Product;
import be.bstorm.tf_java2025_springmvc_exorecap.il.utils.request.SearchParam;
import be.bstorm.tf_java2025_springmvc_exorecap.pl.models.PageDto;
import be.bstorm.tf_java2025_springmvc_exorecap.pl.models.dtos.category.CategoryDto;
import be.bstorm.tf_java2025_springmvc_exorecap.pl.models.dtos.product.ProductDetailDto;
import be.bstorm.tf_java2025_springmvc_exorecap.pl.models.dtos.product.ProductForm;
import be.bstorm.tf_java2025_springmvc_exorecap.pl.models.dtos.product.ProductIndexDto;
import be.bstorm.tf_java2025_springmvc_exorecap.pl.models.dtos.product.ProductUpdateForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public String getPage(
            @RequestParam(required = false) Map<String, String> params,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "name") String sort,
            Model model
    ) {
        List<SearchParam<Product>> searchParams = SearchParam.create(params);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sort));

        Page<Product> result = productService.getProducts(searchParams, pageable);

        List<ProductIndexDto> dtos = result.getContent().stream()
                .map(ProductIndexDto::fromEntity)
                .toList();

        PageDto<ProductIndexDto> pageDto = new PageDto<>(dtos, result.getTotalPages(), result.getNumber());

        List<CategoryDto> categories = categoryService.getCategories()
                .stream()
                .map(CategoryDto::fromCategory)
                .toList();

        model.addAttribute("pageDto", pageDto);
        model.addAttribute("categories", categories);

        return "/product/index";
    }

    @GetMapping("/{id}")
    private String GetDetail(
            @PathVariable Long id,
            Model model) {

        ProductDetailDto product = ProductDetailDto.fromEntity(productService.getProduct(id));

        model.addAttribute("product", product);

        return "/product/detail";
    }

    @GetMapping("/create")
    public String create(Model model) {

        List<CategoryDto> categories = categoryService.getCategories()
                .stream()
                .map(CategoryDto::fromCategory)
                .toList();

        model.addAttribute("form", new ProductForm());
        model.addAttribute("categories", categories);

        return "/product/create";
    }

    @PostMapping("/create")
    public String create(
            @Valid @ModelAttribute("form") ProductForm form,
            BindingResult bindingResult,
            Model model
    ) {

        if(bindingResult.hasErrors()) {

            List<CategoryDto> categories = categoryService.getCategories()
                    .stream()
                    .map(CategoryDto::fromCategory)
                    .toList();

            model.addAttribute("categories", categories);

            return "/product/create";
        }

        Product product = form.ToEntity();

        productService.addProduct(product, form.getImage());

        return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Model model
    ) {

        ProductUpdateForm form = ProductUpdateForm.fromEntity(productService.getProduct(id));

        List<CategoryDto> categories = categoryService.getCategories()
                .stream()
                .map(CategoryDto::fromCategory)
                .toList();

        model.addAttribute("id", id);
        model.addAttribute("form", form);
        model.addAttribute("categories", categories);

        return "/product/edit";
    }

    @PostMapping("/edit/{id}")
    public String create(
            @PathVariable Long id,
            @Valid @ModelAttribute("form") ProductUpdateForm form,
            BindingResult bindingResult,
            Model model
    ) {

        if(bindingResult.hasErrors()) {

            List<CategoryDto> categories = categoryService.getCategories()
                    .stream()
                    .map(CategoryDto::fromCategory)
                    .toList();

            model.addAttribute("id", id);
            model.addAttribute("categories", categories);

            return "/product/edit";
        }

        Product product = form.ToEntity();

        productService.editProduct(id, product, form.getImage());

        return "redirect:/product";
    }

    @PostMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id
    ) {

        productService.deleteProduct(id);
        return "redirect:/product";
    }
}
