package io.traveler.travel.user.controller;


import io.traveler.travel.user.dto.AuthenticatedUserDTO;
import io.traveler.travel.user.dto.input.UpdateUserInput;
import io.traveler.travel.user.dto.request.CreateUserRequest;
import io.traveler.travel.user.dto.request.LoginRequest;
import io.traveler.travel.user.dto.request.UpdateUserRequest;
import io.traveler.travel.user.dto.response.PublicUserResponse;
import io.traveler.travel.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        userService.registerUser(createUserRequest);
    }

    @GetMapping("{id}")
    public PublicUserResponse getUser(@PathVariable long id) {
        return userService.findUserById(id);
    }

    @GetMapping("public/{nickname}")
    public PublicUserResponse getUserInfo(@PathVariable String nickname) {
        return userService.findUserByNickname(nickname);
    }
    

    @PutMapping("{id}")
    public void updateUser(@PathVariable long id, @ModelAttribute @Valid UpdateUserRequest updateUserRequest) {
        MultipartFile profileImage = updateUserRequest.profileImage();
        byte[] imagebytes = transferImageToBytes(profileImage);

        UpdateUserInput input = UpdateUserInput.from(updateUserRequest)
                .withId(id)
                .withProfileImage(imagebytes);

        userService.modifyUserProfile(input);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id) {
        userService.removeUser(id);
    }

    @PostMapping("login")
    public void login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        AuthenticatedUserDTO authenticatedUser = userService.authenticate(loginRequest);
        HttpSession session = request.getSession();
        session.setAttribute("authenticatedUser", authenticatedUser);
    }

    @PostMapping("logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }

    private byte[] transferImageToBytes(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
