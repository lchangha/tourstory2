package io.traveler.travel.user.service;

import io.traveler.travel.user.dto.input.*;
import io.traveler.travel.user.dto.request.*;
import io.traveler.travel.user.dto.response.*;

public interface UserService {

    void registerUser(SignUpRequest createUserRequest);

    PrivateUserResponse findUserById(String email);

    void modifyUserProfile(UpdateUserInput updateUserInput);

    void removeUser(String email);

    PublicUserResponse findUserByNickname(String nickname);

}
