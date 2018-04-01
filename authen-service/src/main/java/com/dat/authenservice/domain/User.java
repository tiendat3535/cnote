package com.dat.authenservice.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USER")
@AttributeOverride(name = "ID", column = @Column(name = "USER_ID"))
public class User extends BaseEntity implements UserDetails {

    public User() {
    }

    public User(@NotNull Long id, @NotNull @Size(max = 255) String username, @NotNull @Size(max = 255) String password) {
        setId(id);
        this.username = username;
        this.password = password;
    }

    public User(@NotNull @Size(max = 255) String username, @NotNull @Size(max = 255) String password) {
        this.username = username;
        this.password = password;
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private VerificationToken verificationToken;

    @NotNull
    @Size(max = 255)
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @NotNull
    @Size(max = 255)
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @NotNull
    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // we never lock accounts
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // credentials never expire
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public VerificationToken getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(VerificationToken verificationToken) {
        this.verificationToken = verificationToken;
        verificationToken.setUser(this);
    }
}
