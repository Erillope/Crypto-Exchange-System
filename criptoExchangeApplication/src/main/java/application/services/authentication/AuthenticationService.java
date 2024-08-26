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
public class AuthenticationService implements SignUpUseCase, SignInUseCase, SignOutUseCase{
    private User signedUser;
    private final SignUpUseCase signUpUserCase;
    private final SignInUseCase signInUserCase;
    private final SignOutUseCase signOutUserCase;
    private final UserRepository userRepository;

    public AuthenticationService(SignUpUseCase signUpUserCase, SignInUseCase signInUserCase, 
            SignOutUseCase signOutUserCase, UserRepository userRepository) {
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
