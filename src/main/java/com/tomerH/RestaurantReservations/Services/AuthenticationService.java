package com.tomerH.RestaurantReservations.Services;

import com.tomerH.RestaurantReservations.Beans.DTO.LoginResponseDTO;
import com.tomerH.RestaurantReservations.Beans.Member;
import com.tomerH.RestaurantReservations.Beans.Role;
import com.tomerH.RestaurantReservations.Beans.UserCredentials;
import com.tomerH.RestaurantReservations.Exceptions.LoginFailedException;
import com.tomerH.RestaurantReservations.Exceptions.RoleException;
import com.tomerH.RestaurantReservations.Repositories.MemberRepository;
import com.tomerH.RestaurantReservations.Repositories.RestaurantRepository;
import com.tomerH.RestaurantReservations.Repositories.RoleRepository;
import com.tomerH.RestaurantReservations.Repositories.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
    @Autowired
    @Lazy
    private TokenService tokenService;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    public AuthenticationService(
            PasswordEncoder passwordEncoder,
            AuthenticationManager authManager) {

        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
    }

//    public UserCredentials userRegistration(String username,
//                                            String password) throws RoleException {
//
//
//    }

    public LoginResponseDTO memberRegistration (String password,
                                      String username,
                                      String firstName,
                                      String lastName,
                                      String phoneNumber,
                                      String email) throws RoleException {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("MEMBER").orElseThrow(RoleException::new);

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        UserCredentials credentials = credentialsRepository.saveAndFlush(new UserCredentials(0,username,encodedPassword,authorities));

        Member newMember = memberRepository.saveAndFlush(
                new Member(firstName,
                        lastName,
                        phoneNumber,
                        email,
                        credentials));

        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,
                            password)
            );

            String token = tokenService.generateJwt(auth);
            return new LoginResponseDTO(credentials,token);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
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

