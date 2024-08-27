package com.globant.application.services.authentication;

import com.globant.application.dto.SignInDTO;
import com.globant.application.dto.SignUpDTO;
import com.globant.application.repositories.UserRepository;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.user.User;

/**
 *
 * @author erillope
 */
public class AuthenticationService implements SignUpUseCase, SignInUseCase, SignOutUseCase{
    private User signedUser;
    private final SignUpUseCase signUpUserCase;
    private final SignInUseCase signInUserCase;
    private final UserRepository userRepository;

    public AuthenticationService(SignUpUseCase signUpUserCase, SignInUseCase signInUserCase, UserRepository userRepository) {
        this.signUpUserCase = signUpUserCase;
        this.signInUserCase = signInUserCase;
        this.userRepository = userRepository;
    }
    
    public User getSignedUser(){return signedUser;}

    @Override
    public void signUp(SignUpDTO dto) throws DomainException{
        this.signUpUserCase.signUp(dto);
    }

    @Override
    public void signIn(SignInDTO dto) throws DomainException {
        this.signInUserCase.signIn(dto);
        signedUser = userRepository.getByEmail(dto.getEmail());
    }

    @Override
    public void signOut() {
        signedUser = null;
    }
}
