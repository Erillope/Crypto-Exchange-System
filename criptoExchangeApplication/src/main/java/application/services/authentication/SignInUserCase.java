package application.services.authentication;

import application.dto.SignInDTO;

/**
 *
 * @author erillope
 */
public interface SignInUserCase {
    public void signIn(SignInDTO dto);
}
