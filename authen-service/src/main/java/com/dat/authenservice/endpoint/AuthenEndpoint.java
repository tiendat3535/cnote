package com.dat.authenservice.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "api/v1/auth", produces = "application/json")
public class AuthenEndpoint {

    @GetMapping(value = "user")
    public Principal user(Principal principal) {
        return principal;
    }

}
