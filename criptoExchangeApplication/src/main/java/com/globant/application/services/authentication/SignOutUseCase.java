package com.globant.application.services.authentication;

import com.globant.application.dto.SignOutDTO;

/**
 *
 * @author erillope
 */
public interface SignOutUseCase {
    public void signOut(SignOutDTO dto);
}
