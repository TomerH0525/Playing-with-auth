package com.tomerH.RestaurantReservations.Services;

import com.tomerH.RestaurantReservations.Beans.DTO.LoginResponseDTO;
import com.tomerH.RestaurantReservations.Beans.Role;
import com.tomerH.RestaurantReservations.Beans.UserCredentials;
import com.tomerH.RestaurantReservations.Exceptions.LoginFailedException;
import com.tomerH.RestaurantReservations.Repositories.RoleRepository;
import com.tomerH.RestaurantReservations.Repositories.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    @Lazy
    private UserCredentialsRepository credentialsRepository;
    @Autowired
    @Lazy
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    @Autowired
    @Lazy
    private TokenService tokenService;

    public AuthenticationService(
            PasswordEncoder passwordEncoder,
            AuthenticationManager authManager) {

        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
    }

    public UserCredentials registerMember(String username, String password)throws Exception{

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("MEMBER").orElse(new Role(4,"VISITOR"));

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        return credentialsRepository.save(new UserCredentials(0,username,encodedPassword,authorities));
    }


    public LoginResponseDTO loginMember(String username, String password) throws LoginFailedException {

        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(credentialsRepository.findByUsername(username).get(),token);

        }catch (Exception e){
          throw new LoginFailedException("blah blah");
        }
    }



    private boolean checkPasswordStrenght(String password) {
        boolean hasLowerCase = false;
        boolean hasUpperCase = false;

        if (password.length() < 8) {
            return false;
        } else {
            for (char c : password.toCharArray()) {

                if (hasLowerCase && hasUpperCase) {
                    return true;
                } else if (Character.isLowerCase(c)) {
                    hasLowerCase = true;
                } else if (Character.isUpperCase(c)) {
                    hasUpperCase = true;
                }
            }
            return false;
        }
    }
}

