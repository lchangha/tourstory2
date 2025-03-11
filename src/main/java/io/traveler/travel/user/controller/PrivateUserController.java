package io.traveler.travel.user.controller;


import io.traveler.travel.common.utils.FileUtil;
import io.traveler.travel.user.dto.AuthenticatedUserDTO;
import io.traveler.travel.user.dto.input.UpdateUserInput;
import io.traveler.travel.user.dto.request.CreateUserRequest;
import io.traveler.travel.user.dto.request.LoginRequest;
import io.traveler.travel.user.dto.request.UpdateUserRequest;
import io.traveler.travel.user.dto.response.PrivateUserResponse;
import io.traveler.travel.user.dto.response.PublicUserResponse;
import io.traveler.travel.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("api/user/private")
public class PrivateUserController {
    private final UserService userService;
    private final HttpSession httpSession;

    public PrivateUserController(UserService userService, HttpSession httpSession) {
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @PostMapping
    public void createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        userService.registerUser(createUserRequest);
    }

    @GetMapping("me")
    public PrivateUserResponse getMe() {
        AuthenticatedUserDTO user = (AuthenticatedUserDTO) httpSession.getAttribute("AuthenticatedUser");
        return userService.findUserById(user.id());
    }

    @GetMapping("{id}")
    public PrivateUserResponse getUser(@PathVariable long id) {
        return userService.findUserById(id);
    }


    @PutMapping("{id}")
    public void updateUser(@PathVariable long id, @ModelAttribute @Valid UpdateUserRequest updateUserRequest) {
        MultipartFile profileImage = updateUserRequest.profileImage();
        byte[] imageBytes = FileUtil.transferImageToBytes(profileImage);

        UpdateUserInput input = UpdateUserInput.from(updateUserRequest)
                .withId(id)
                .withProfileImage(imageBytes);

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

}
