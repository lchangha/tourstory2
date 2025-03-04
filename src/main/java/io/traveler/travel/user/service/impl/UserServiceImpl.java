package io.traveler.travel.user.service.impl;

import io.traveler.travel.user.dto.AuthenticatedUserDTO;
import io.traveler.travel.user.dto.input.UpdateUserInput;
import io.traveler.travel.user.dto.request.CreateUserRequest;
import io.traveler.travel.user.dto.request.LoginRequest;
import io.traveler.travel.user.dto.response.UserResponse;
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
                .orElseThrow();
    }

    @Override
    public void registerUser(CreateUserRequest createUserRequest) {
        User userRequest = User.builder()
                .email(createUserRequest.email())
                .name(createUserRequest.name())
                .nickname(createUserRequest.nickname())
                .password(createUserRequest.password())
                .phone(createUserRequest.phone())
                .birth(createUserRequest.birth())
                .gender(createUserRequest.gender())
                .build();

        userRepository.save(userRequest);

    }

    @Override
    public UserResponse findUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow();

        return null;
    }

    @Override
    public void modifyUserProfile(UpdateUserInput input) {
        User user = userRepository.findById(input.id()).orElseThrow();

    }

    @Override
    public void removeUser(long id) {

    }

}
