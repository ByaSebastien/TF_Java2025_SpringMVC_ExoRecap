package be.bstorm.tf_java2025_springmvc_exorecap.il.utils.initializers;

import be.bstorm.tf_java2025_springmvc_exorecap.dal.repositories.CategoryRepository;
import be.bstorm.tf_java2025_springmvc_exorecap.dal.repositories.ProductRepository;
import be.bstorm.tf_java2025_springmvc_exorecap.dal.repositories.UserRepository;
import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.Category;
import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.Product;
import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.User;
import be.bstorm.tf_java2025_springmvc_exorecap.dl.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        if(userRepository.count() == 0) {

            String password = passwordEncoder.encode("Test1234=");

            List<User> users = List.of(
                    new User("admin@test.be","Admin", password, UserRole.ADMIN),
                    new User("user@test.be","User", password, UserRole.USER)
            );

            userRepository.saveAll(users);
        }

        if(categoryRepository.count() == 0) {

            List<Category> categories = List.of(
                    new Category("Électronique"),
                    new Category("Alimentation"),
                    new Category("Vêtements"),
                    new Category("Livres"),
                    new Category("Meubles")
            );
            categoryRepository.saveAll(categories);
        }

        if(productRepository.count() == 0) {

            Category electronique = categoryRepository.findByName("Électronique").orElseThrow();
            Category alimentation = categoryRepository.findByName("Alimentation").orElseThrow();
            Category vetements = categoryRepository.findByName("Vêtements").orElseThrow();
            Category livres = categoryRepository.findByName("Livres").orElseThrow();
            Category meubles = categoryRepository.findByName("Meubles").orElseThrow();

            List<Product> products = List.of(
                    new Product("iPhone 15", "Smartphone Apple dernier cri", 120000, electronique),
                    new Product("Samsung Galaxy S23", "Smartphone Samsung haute performance", 99900, electronique),
                    new Product("Laptop Dell XPS 15", "Ordinateur portable performant", 150000, electronique),
                    new Product("Pain complet", "Pain artisanal complet bio", 300, alimentation),
                    new Product("Chocolat noir", "Tablette de chocolat 70% cacao", 200, alimentation),
                    new Product("T-shirt coton bio", "T-shirt confortable et écologique", 2000, vetements),
                    new Product("Jean slim", "Jean tendance pour hommes", 4000, vetements),
                    new Product("Le Petit Prince", "Livre classique de Saint-Exupéry", 1200, livres),
                    new Product("Harry Potter tome 1", "Roman jeunesse populaire", 1500, livres),
                    new Product("Chaise en bois", "Chaise confortable pour salon", 5000, meubles),
                    new Product("Table basse", "Table basse en bois massif", 12000, meubles)
            );

            productRepository.saveAll(products);
        }
    }
}
