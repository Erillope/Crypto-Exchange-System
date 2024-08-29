package com.globant.application.services.authentication;

import com.globant.application.dto.SignInDTO;
import com.globant.application.dto.SignUpDTO;
import com.globant.application.dto.UserDTO;
import com.globant.application.repositories.UserRepository;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.user.User;

/**
 *
 * @author erillope
 */
public class AuthenticationService implements SignUpUseCase, SignInUseCase, SignOutUseCase{
    private UserDTO signedUserDTO;
    private final SignUpUseCase signUpUserCase;
    private final SignInUseCase signInUserCase;
    private final UserRepository userRepository;

    public AuthenticationService(SignUpUseCase signUpUserCase, SignInUseCase signInUserCase, UserRepository userRepository) {
        this.signUpUserCase = signUpUserCase;
        this.signInUserCase = signInUserCase;
        this.userRepository = userRepository;
    }
    
    public UserDTO getSignedUserDTO(){return signedUserDTO;}

    @Override
    public void signUp(SignUpDTO dto) throws DomainException{
        this.signUpUserCase.signUp(dto);
    }

    @Override
    public void signIn(SignInDTO dto) throws DomainException {
        this.signInUserCase.signIn(dto);
        User signedUser = userRepository.getByEmail(dto.getEmail());
        signedUserDTO = new UserDTO(signedUser.getNumberAccount().getNumberAccount(), signedUser.getUserID(), signedUser.getWalletID(),
        signedUser.getUserAccount().getName(), signedUser.getUserAccount().getEmail());
    }

    @Override
    public void signOut() {
        signedUserDTO = null;
    }
}
