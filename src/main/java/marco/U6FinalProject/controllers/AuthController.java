package marco.U6FinalProject.controllers;

import marco.U6FinalProject.exceptions.BadRequestException;
import marco.U6FinalProject.payloads.NewUserDTO;
import marco.U6FinalProject.payloads.NewUserResponseDTO;
import marco.U6FinalProject.payloads.UserLoginDTO;
import marco.U6FinalProject.payloads.UserLoginResponseDTO;
import marco.U6FinalProject.services.AuthService;
import marco.U6FinalProject.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UsersService usersService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO save(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return new NewUserResponseDTO(this.usersService.save(body).getId());
    }

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        return new UserLoginResponseDTO(this.authService.authUserAndCreateToken(body));
    }
}
