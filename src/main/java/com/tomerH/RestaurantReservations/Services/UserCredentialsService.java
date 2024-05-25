package com.tomerH.RestaurantReservations.Services;

import com.tomerH.RestaurantReservations.Beans.UserCredentials;
import com.tomerH.RestaurantReservations.Exceptions.UserRegistrationError;
import com.tomerH.RestaurantReservations.Exceptions.WeakPasswordExeption;
import com.tomerH.RestaurantReservations.Repositories.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsService implements UserDetailsService {

    private UserCredentialsRepository credentialsRepository;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserCredentialsService(UserCredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

//    public Member registerMember(Member member) throws Exception {
//        if (member.getId() == 0 && member.getCredentials().getId() == 0) {
//            if (checkPasswordStrenght(member.getCredentials().getPassword())){
//                if (member.getCredentials().getUsername().length() > 3){
//                    credentialsRepository.save(member);
//                }else throw new UserRegistrationError("Username must be at least 4 characters");
//            } else throw new WeakPasswordExeption();
//        } else throw new UserRegistrationError("ID cannot be set ERROR");
//
//        return member;
//    }

    public long registerUser(UserCredentials user)throws Exception{
        if (user.getId() == 0){
            if (checkPasswordStrenght(user.getPassword())){
                if (user.getUsername().length() >= 4){
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    return credentialsRepository.saveAndFlush(user).getId();
                }else throw new UserRegistrationError("Username must be at least 4 character long");
            }else throw new WeakPasswordExeption();
        }else throw new UserRegistrationError("ID cannot be set ERROR (<> 0)");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredentials user = credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username was not found " + username));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(getRoles(user))
                .build();
    }

    private String[] getRoles(UserCredentials user) {
        if (user.getUserRole() == null) {
            return new String[]{"Visitor"};

        } else return user.getUserRole().name().split(",");
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
