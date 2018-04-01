package com.dat.authenservice.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "VERIFICATION_TOKEN")
@AttributeOverride(name = "ID", column = @Column(name = "VERIFICATION_TOKEN_ID"))
public class VerificationToken extends BaseEntity {

    @NotNull
    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false, updatable = false)
    private User user;

    @NotNull
    @Size(max = 255)
    @Column(name = "TOKEN", nullable = false)
    private String token;

    @NotNull
    @Column(name = "EXPIRED_DATE")
    private LocalDate expiredDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }
}