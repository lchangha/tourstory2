package io.traveler.travel.user.controller;


import io.traveler.travel.global.utils.*;
import io.traveler.travel.user.dto.input.*;
import io.traveler.travel.user.dto.request.*;
import io.traveler.travel.user.dto.response.*;
import io.traveler.travel.user.service.*;
import jakarta.validation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;


@RestController
@RequestMapping("api/user/private")
public class PrivateUserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public PrivateUserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("me")
    public PrivateUserResponse getMe(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.findUserByEmail(userDetails.getUsername());
    }

    @PutMapping("me")
    public void updateUser(@AuthenticationPrincipal UserDetails userDetails,
                           @ModelAttribute @Valid UpdateUserRequest updateUserRequest) {
        if (userDetails.getUsername() == updateUserRequest.email()) {
            MultipartFile profileImage = updateUserRequest.profileImage();
            byte[] imageBytes = FileUtil.transferImageToBytes(profileImage);

            UpdateUserInput input = UpdateUserInput.from(updateUserRequest)
                    .withProfileImage(imageBytes);

            userService.modifyUserProfile(input);

        }
    }

    @DeleteMapping("me")
    public void deleteUser(@PathVariable long id) {
        userService.removeUser(id);
    }


}
