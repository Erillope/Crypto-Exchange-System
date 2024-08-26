package application.repositories;

import models.user.User;
import models.user.UserID;

/**
 *
 * @author USER
 */
public interface UserRepository extends Repository<UserID, User>{
    public User getByEmail(String email);
    
    public boolean containEmail(String email);
}
