package com.globant.application.services.authentication;

import com.globant.application.dto.SignInDTO;

/**
 *
 * @author erillope
 */
public interface SignInUseCase {
    public void signIn(SignInDTO dto);
}
