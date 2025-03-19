package io.traveler.travel.user.controller;


import io.traveler.travel.common.utils.*;
import io.traveler.travel.user.dto.input.*;
import io.traveler.travel.user.dto.request.*;
import io.traveler.travel.user.dto.response.*;
import io.traveler.travel.user.service.*;
import jakarta.servlet.http.*;
import jakarta.validation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.context.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;


@RestController
@RequestMapping("api/user/private")
public class PrivateUserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public PrivateUserController(UserService userService, HttpSession httpSession, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.httpSession = httpSession;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public void createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        userService.registerUser(createUserRequest);
    }

//    @GetMapping("me")
//    public PrivateUserResponse getMe(@AuthenticationPrincipal UserDetails userDetails) {
//        return userService.findUserByEmail(userDetails.getId());
//    }

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
    public void login(@RequestBody LoginRequest loginRequest) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.email(), loginRequest.password()
            );

            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);


            session.setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext()
            );
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } finally {
//            명시적으로 클리어 해줘야한다면 로그아웃 로직에서 클리어컨텍스트 하는것보다 리소스를 사용하는쪽에서 클리어 해주는게 더 좋은거같은데?
//            물론 이러면 이제 SecurityContextHolder로 컨텍스트를 쓰면 항상 클리어 해줘야할듯? 그러면 관리할 포인트가 늘어나나?
//            try-with-resources 를 쓰려면 AutoCloseable 라는 인터페이스를 구현했어야했음? SecurityContextHolderStrategy가
            SecurityContextHolder.clearContext();
        }


    }

    @PostMapping("logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
//        SecurityContextHolder.clearContext();
    }

}
