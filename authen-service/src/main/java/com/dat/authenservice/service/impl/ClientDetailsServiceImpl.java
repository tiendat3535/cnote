package com.dat.authenservice.service.impl;

import com.dat.authenservice.common.Constants;
import com.dat.authenservice.repository.ClientRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
@Primary
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        BaseClientDetails baseClientDetails = clientRepository
                .findByClientId(clientId)
                .map(client -> {
                    BaseClientDetails details = new BaseClientDetails(
                            client.getClientId(),
                            client.getResourceIds(),
                            client.getScope(),
                            client.getAuthorizedGrantTypes(),
                            client.getAuthorities(),
                            client.getRegisteredRedirectUri()
                    );
                    details.setClientSecret(client.getClientSecret());
                    details.setAccessTokenValiditySeconds(client.getAccessTokenValiditySeconds());
                    String[] arrayScope = StringUtils.split(client.getAutoApproveScopes(), Constants.COMMA_SEPARATOR);
                    details.setAutoApproveScopes(arrayScope != null ? Arrays.asList(arrayScope) : Collections.<String>emptyList());
                    return details;
                })
                .orElseThrow(() -> new ClientRegistrationException(String.format("No client %clientId registered", clientId)));
        return baseClientDetails;
    }

}
