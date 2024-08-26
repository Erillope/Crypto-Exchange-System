package com.globant.application.services.authentication;

import com.globant.application.dto.SignInDTO;
import com.globant.application.dto.SignOutDTO;
import com.globant.application.dto.SignUpDTO;
import com.globant.application.repositories.UserRepository;
import com.globant.domain.user.User;

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
