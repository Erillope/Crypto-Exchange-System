package com.globant.application.services.authentication;

import com.globant.application.dto.SignUpDTO;

/**
 *
 * @author erillope
 */
public interface SignUpUseCase {
    public void signUp(SignUpDTO dto);
}
