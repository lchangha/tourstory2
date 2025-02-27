package io.traveler.travel.user.controller;


import io.traveler.travel.user.dto.AuthenticatedUserDTO;
import io.traveler.travel.user.dto.request.CreateUserRequest;
import io.traveler.travel.user.dto.request.LoginRequest;
import io.traveler.travel.user.dto.request.UpdateUserRequest;
import io.traveler.travel.user.dto.response.UserResponse;
import io.traveler.travel.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {

    }

    @GetMapping("{id}")
    public UserResponse getUser(@PathVariable int id) {

    }

    @PutMapping("{id}")
    public void updateUser(@PathVariable int id, @RequestBody @Valid UpdateUserRequest updateUserRequest) {

    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable int id) {

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
