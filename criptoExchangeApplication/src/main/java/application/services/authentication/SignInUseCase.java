package application.services.authentication;

import application.dto.SignInDTO;

/**
 *
 * @author erillope
 */
public interface SignInUseCase {
    public void signIn(SignInDTO dto);
}
