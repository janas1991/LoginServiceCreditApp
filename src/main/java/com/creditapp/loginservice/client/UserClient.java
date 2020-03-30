package com.creditapp.loginservice.client;

import com.creditapp.loginservice.domain.UserDto;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

public class UserClient {

    private RestTemplate restTemplate = new RestTemplate();

    public UserClient() {
    }

    URI saveURL = UriComponentsBuilder.fromHttpUrl("http://localhost:8009/user/").build().encode().toUri();

    public List<UserDto> getUsers() {
        URI getURL = UriComponentsBuilder.fromHttpUrl("http://localhost:8009/user/").build().encode().toUri();
        try {
            UserDto[] usersResponse = restTemplate.getForObject(getURL, UserDto[].class);
            return Arrays.asList(ofNullable(usersResponse).orElse(new UserDto[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public UserDto saveUser(UserDto userDto) {
        return restTemplate.postForObject(saveURL, userDto, UserDto.class);
    }

}
