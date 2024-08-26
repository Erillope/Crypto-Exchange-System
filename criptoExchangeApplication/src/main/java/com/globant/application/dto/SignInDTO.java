package application.dto;

/**
 *
 * @author erillope
 */
public class SignInDTO {
    private final String email;
    private final String password;
    
    public SignInDTO(String email, String password){
        this.email = email;
        this.password = password;
    }
    
    public String getEmail(){return email;}
    
    public String getPassword(){return password;}
}
