package io.traveler.travel.user.service;

import io.traveler.travel.user.dto.AuthenticatedUserDTO;
import io.traveler.travel.user.dto.request.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    AuthenticatedUserDTO authenticate(LoginRequest loginRequest);
}
