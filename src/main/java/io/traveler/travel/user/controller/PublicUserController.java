package io.traveler.travel.user.controller;

import io.traveler.travel.user.dto.request.*;
import io.traveler.travel.user.dto.response.PublicUserResponse;
import io.traveler.travel.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user/public")
public class PublicUserController {
    private final UserService userService;

    public PublicUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{nickname}")
    public PublicUserResponse getUserInfo(@PathVariable String nickname) {
        return userService.findUserByNickname(nickname);
    }

    @PostMapping("signup")
    public void signUp(@RequestBody SignUpRequest signUpRequest) {
        userService.registerUser(signUpRequest);
    }
}
