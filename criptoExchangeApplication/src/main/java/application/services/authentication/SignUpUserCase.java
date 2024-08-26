package application.services.authentication;

import application.dto.SignUpDTO;

/**
 *
 * @author erillope
 */
public interface SignUpUserCase {
    public void signUp(SignUpDTO dto);
}
