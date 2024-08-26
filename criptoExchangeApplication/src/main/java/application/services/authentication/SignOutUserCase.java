package application.services.authentication;

import application.dto.SignOutDTO;

/**
 *
 * @author erillope
 */
public interface SignOutUserCase {
    public void signOut(SignOutDTO dto);
}
