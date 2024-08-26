package application.services.authentication;

import application.dto.SignOutDTO;

/**
 *
 * @author erillope
 */
public interface SignOutUseCase {
    public void signOut(SignOutDTO dto);
}
