package be.bstorm.tf_java2025_springmvc_exorecap.bll.services.security;

import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    void register(User user);

}
