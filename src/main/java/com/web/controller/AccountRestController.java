package com.web.controller;

import com.web.domain.User;
import com.web.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.web.domain.enums.SocialType.DEFAULT;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {

    private UserRepository userRepository;

    public AccountRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> postAccount(@RequestBody User user) {
        //valid 체크

        user.setSocialType(DEFAULT);
        user.setCreatedDateNow();

        User domainUser = userRepository.findByEmail(user.getEmail());

        if (domainUser != null) {
            if (domainUser.getSocialType().equals(DEFAULT)) {
                return null;
            } else {
                userRepository.save(user);
            }
        } else {
            userRepository.save(user);
        }

        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }
}
