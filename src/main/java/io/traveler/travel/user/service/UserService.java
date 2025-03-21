package io.traveler.travel.user.service;

import io.traveler.travel.user.dto.input.*;
import io.traveler.travel.user.dto.request.*;
import io.traveler.travel.user.dto.response.*;

public interface UserService {

    void registerUser(SignUpRequest createUserRequest);

    PrivateUserResponse findUserById(long id);

    void modifyUserProfile(UpdateUserInput updateUserInput);

    void removeUser(long id);

    PublicUserResponse findUserByNickname(String nickname);

    PrivateUserResponse findUserByEmail(String username);
}
