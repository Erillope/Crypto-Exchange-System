package application.services.authentication;

import application.dto.SignUpDTO;

/**
 *
 * @author erillope
 */
public interface SignUpUseCase {
    public void signUp(SignUpDTO dto);
}
