package marco.U6FinalProject.services;

import marco.U6FinalProject.entities.User;
import marco.U6FinalProject.exceptions.UnauthorizedException;
import marco.U6FinalProject.payloads.UserLoginDTO;
import marco.U6FinalProject.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsersService usersService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;

    public String authUserAndCreateToken(UserLoginDTO body) {
        User user = this.usersService.findByEmail(body.email());

        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("password errata");
        }
    }

}
