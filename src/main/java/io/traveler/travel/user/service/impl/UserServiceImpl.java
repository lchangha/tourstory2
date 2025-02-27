package io.traveler.travel.user.service.impl;

import io.traveler.travel.user.dto.AuthenticatedUserDTO;
import io.traveler.travel.user.dto.request.LoginRequest;
import io.traveler.travel.user.entity.User;
import io.traveler.travel.user.repository.UserRepository;
import io.traveler.travel.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthenticatedUserDTO authenticate(LoginRequest loginRequest) {
        Optional<User> authenticatedUser = userRepository.findByEmailAndPassword(loginRequest.email(), loginRequest.password());

        return authenticatedUser
                .map(user -> new AuthenticatedUserDTO(user.getId(), user.getEmail()))
                .orElseThrow(() -> new RuntimeException("인증 실패"));
    }

}
