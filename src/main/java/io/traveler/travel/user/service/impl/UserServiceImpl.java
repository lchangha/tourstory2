package io.traveler.travel.user.service.impl;

import io.traveler.travel.image.ImageUploader;
import io.traveler.travel.user.dto.input.UpdateUserInput;
import io.traveler.travel.user.dto.request.CreateUserRequest;
import io.traveler.travel.user.dto.response.PrivateUserResponse;
import io.traveler.travel.user.dto.response.PublicUserResponse;
import io.traveler.travel.user.entity.User;
import io.traveler.travel.user.repository.UserRepository;
import io.traveler.travel.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ImageUploader imageUploader;

    public UserServiceImpl(UserRepository userRepository, ImageUploader imageUploader) {
        this.userRepository = userRepository;
        this.imageUploader = imageUploader;
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
    public PublicUserResponse findUserByNickname(String nickname) {
        return userRepository.findBynickname(nickname)
                .map(PublicUserResponse::from)
                .orElseThrow();
    }

    @Override
    public PrivateUserResponse findUserById(long id) {
        return userRepository.findById(id)
                .map(PrivateUserResponse::from)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void modifyUserProfile(UpdateUserInput input) {
        User user = userRepository.findById(input.id())
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
    public void removeUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow();
        user.delete();
    }


}
