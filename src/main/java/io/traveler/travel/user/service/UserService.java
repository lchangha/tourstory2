package io.traveler.travel.user.service;

import io.traveler.travel.user.dto.input.UpdateUserInput;
import io.traveler.travel.user.dto.request.CreateUserRequest;
import io.traveler.travel.user.dto.response.PrivateUserResponse;
import io.traveler.travel.user.dto.response.PublicUserResponse;

public interface UserService {

    void registerUser(CreateUserRequest createUserRequest);

    PrivateUserResponse findUserById(long id);

    void modifyUserProfile(UpdateUserInput updateUserInput);

    void removeUser(long id);

    PublicUserResponse findUserByNickname(String nickname);
}
