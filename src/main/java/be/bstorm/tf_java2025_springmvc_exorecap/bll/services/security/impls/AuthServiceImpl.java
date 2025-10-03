package be.bstorm.tf_java2025_springmvc_exorecap.bll.services.security.impls;

import be.bstorm.tf_java2025_springmvc_exorecap.bll.services.security.AuthService;
import be.bstorm.tf_java2025_springmvc_exorecap.dal.repositories.UserRepository;
import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.User;
import be.bstorm.tf_java2025_springmvc_exorecap.dl.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(User user) {

        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("User with email " + user.getEmail() + " Already Exists.");
        }

        user.setRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User with email " + email + " Not Found.")
        );
    }
}
