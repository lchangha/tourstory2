package io.traveler.travel.user.service.impl;

import io.traveler.travel.image.*;
import io.traveler.travel.user.dto.input.*;
import io.traveler.travel.user.dto.request.*;
import io.traveler.travel.user.dto.response.*;
import io.traveler.travel.user.entity.*;
import io.traveler.travel.user.repository.*;
import io.traveler.travel.user.service.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ImageUploader imageUploader;

    public UserServiceImpl(UserRepository userRepository, ImageUploader imageUploader) {
        this.userRepository = userRepository;
        this.imageUploader = imageUploader;
    }



    @Override
    public void registerUser(SignUpRequest createUserRequest) {
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
    public PublicUserResponse findUserByNickname(String nickname) {
        return userRepository.findBynickname(nickname)
                .map(PublicUserResponse::from)
                .orElseThrow();
    }

    @Override
    public PrivateUserResponse findUserById(String email) {
        return userRepository.findById(email)
                .map(PrivateUserResponse::from)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void modifyUserProfile(UpdateUserInput input) {
        User user = userRepository.findById(input.email())
                .orElseThrow();

        if (input.email() != null) {
            user.updateEmail(input.email());
        }
        if (input.name() != null) {
            user.updateName(input.name());
        }
        if (input.nickname() != null) {
            user.updateNickname(input.nickname());
        }
        if (input.password() != null) {
            user.updatePassword(input.password());
        }
        if (input.phone() != null) {
            user.updatePhone(input.phone());
        }
        if (input.birth() != null) {
            user.updateBirth(input.birth());
        }
        if (input.profileImage() != null) {
            String profileUrl = imageUploader.handleUpload(input.profileImage());
            user.updateProFileUrl(profileUrl);
        }
    }

    @Override
    public void removeUser(String email) {
        User user = userRepository.findById(email)
                .orElseThrow();
        user.delete();
    }


}
