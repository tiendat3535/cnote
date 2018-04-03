package com.dat.authenservice;

import com.dat.authenservice.domain.Client;
import com.dat.authenservice.domain.User;
import com.dat.authenservice.repository.ClientRepository;
import com.dat.authenservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableResourceServer
@EnableAuthorizationServer
@EnableEurekaClient
@EnableTransactionManagement
public class AuthenService implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(AuthenService.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        User user = new User("dat", passwordEncoder.encode("123456"));
        userRepository.save(user);

        Client client = Client.builder()
                .clientId("my-trusted-client")
                .authorizedGrantTypes("client_credentials,refresh_token,password")
                .authorities("ROLE_CLIENT,ROLE_TRUSTED_CLIENT")
                .scope("read,write")
                .resourceIds("oauth2-resource")
                .accessTokenValiditySeconds(5000)
                .clientSecret(passwordEncoder.encode("secret"))
                .build();
        clientRepository.save(client);
    }
}
