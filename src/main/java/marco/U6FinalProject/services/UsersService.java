package marco.U6FinalProject.services;

import marco.U6FinalProject.entities.User;
import marco.U6FinalProject.exceptions.BadRequestException;
import marco.U6FinalProject.exceptions.NotFoundException;
import marco.U6FinalProject.payloads.NewUserDTO;
import marco.U6FinalProject.repositories.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersDAO usersDAO;


    public User save(NewUserDTO body) throws BadRequestException {
        this.usersDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email è già registrata");
        });
        User newUser = new User(body.name(), body.surname(), body.email(), body.password());
        return usersDAO.save(newUser);
    }

    public User findById(Long userId) {
        return this.usersDAO.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }
}
