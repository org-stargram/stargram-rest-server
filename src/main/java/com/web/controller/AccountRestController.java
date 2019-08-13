package com.web.controller;

import com.web.domain.Board;
import com.web.domain.User;
import com.web.repository.BoardRepository;
import com.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.web.domain.enums.SocialType.DEFAULT;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
        userRepository.save(user);

        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }
}
