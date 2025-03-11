package io.traveler.travel.user.controller;

import io.traveler.travel.user.dto.response.PublicUserResponse;
import io.traveler.travel.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
