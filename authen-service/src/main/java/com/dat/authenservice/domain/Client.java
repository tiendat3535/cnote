package com.dat.authenservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLIENT")
@AttributeOverride(name = "ID", column = @Column(name = "CLIENT_ID"))
public class Client extends BaseEntity {

    @Column(name = "CLIENT_ID")
    private String clientId;

    @Column(name = "RESOURCE_IDS")
    private String resourceIds;

    @Column(name = "SECRET_REQUIRED")
    private boolean secretRequired;

    @NotNull
    @Column(name = "CLIENT_SECRET")
    private String clientSecret;

    @Column(name = "SCOPED")
    private boolean scoped;

    @NotNull
    @Column(name = "SCOPE")
    private String scope;

    @NotNull
    @Column(name = "AUTHORIZED_GRANT_TYPE")
    private String authorizedGrantTypes;

    @Column(name = "REGISTERED_REDIRECT_URI")
    private String registeredRedirectUri;

    @NotNull
    @Column(name = "AUTHORITIES")
    private String authorities;

    @Column(name = "ACCESS_TOKEN_VALIDITY_SECONDS")
    private Integer accessTokenValiditySeconds;

    @Column(name = "REFRESH_TOKEN_VALIDITY_SECONDS")
    private Integer refreshTokenValiditySeconds;

    @Column(name = "AUTO_APPROVE_SCOPES")
    private String autoApproveScopes;

    @Column(name = "ADDITIONAL_INFORMATION")
    private String additionalInformation;

}
