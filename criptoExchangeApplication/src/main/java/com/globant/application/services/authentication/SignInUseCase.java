package com.globant.application.services.authentication;

import com.globant.application.dto.SignInDTO;
import com.globant.domain.exceptions.DomainException;

/**
 *
 * @author erillope
 */
public interface SignInUseCase {
    public void signIn(SignInDTO dto) throws DomainException;
}
