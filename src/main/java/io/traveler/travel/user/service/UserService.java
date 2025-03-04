package io.traveler.travel.user.service;

import io.traveler.travel.user.dto.AuthenticatedUserDTO;
import io.traveler.travel.user.dto.input.UpdateUserInput;
import io.traveler.travel.user.dto.request.CreateUserRequest;
import io.traveler.travel.user.dto.request.LoginRequest;
import io.traveler.travel.user.dto.request.UpdateUserRequest;
import io.traveler.travel.user.dto.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    AuthenticatedUserDTO authenticate(LoginRequest loginRequest);

    void registerUser(CreateUserRequest createUserRequest);

    UserResponse findUserById(long id);

    void modifyUserProfile(UpdateUserInput updateUserInput);

    void removeUser(long id);
}
