package com.globant.application.services.authentication;

import com.globant.application.dto.SignUpDTO;
import com.globant.domain.exceptions.DomainException;

/**
 *
 * @author erillope
 */
public interface SignUpUseCase {
    public void signUp(SignUpDTO dto) throws DomainException;
}
