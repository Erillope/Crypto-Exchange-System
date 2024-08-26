package application.services.authentication;

import application.dto.SignInDTO;
import application.dto.SignOutDTO;
import application.dto.SignUpDTO;
import application.repositories.UserRepository;
import models.user.User;

/**
 *
 * @author erillope
 */
public class AuthenticationService implements SignUpUserCase, SignInUserCase, SignOutUserCase{
    private User signedUser;
    private final SignUpUserCase signUpUserCase;
    private final SignInUserCase signInUserCase;
    private final SignOutUserCase signOutUserCase;
    private final UserRepository userRepository;

    public AuthenticationService(SignUpUserCase signUpUserCase, SignInUserCase signInUserCase, 
            SignOutUserCase signOutUserCase, UserRepository userRepository) {
        this.signUpUserCase = signUpUserCase;
        this.signInUserCase = signInUserCase;
        this.signOutUserCase = signOutUserCase;
        this.userRepository = userRepository;
    }
    
    public User getSignedUser(){return signedUser;}

    @Override
    public void signUp(SignUpDTO dto) {
        this.signUpUserCase.signUp(dto);
        signedUser = userRepository.getByEmail(dto.getEmail());
    }

    @Override
    public void signIn(SignInDTO dto) {
        this.signInUserCase.signIn(dto);
    }

    @Override
    public void signOut(SignOutDTO dto) {
        this.signOutUserCase.signOut(dto);
        signedUser = null;
    }
}
