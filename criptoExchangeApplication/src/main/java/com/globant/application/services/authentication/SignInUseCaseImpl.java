package com.globant.application.services.authentication;

import com.globant.application.dto.SignInDTO;
import com.globant.application.repositories.UserRepository;
import com.globant.domain.exceptions.DomainException;
import com.globant.domain.exceptions.InvalidPasswordException;
import com.globant.domain.user.User;

/**
 *
 * @author erillope
 */
public class SignInUseCaseImpl implements SignInUseCase{
    private final UserRepository userRepository;

    public SignInUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public void signIn(SignInDTO dto) throws DomainException{
        User user = userRepository.getByEmail(dto.getEmail());
        validatePassword(user.getUserAccount().getPassword(), dto.getPassword());
    }
    
    private void validatePassword(String userPassword, String password) throws InvalidPasswordException{
        if (!password.equals(userPassword))
        {throw InvalidPasswordException.incorrectPassword();}
    }
    
}
