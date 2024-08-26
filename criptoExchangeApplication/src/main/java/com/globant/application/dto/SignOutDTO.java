package com.globant.application.dto;

/**
 *
 * @author erillope
 */
public class SignOutDTO {
    private final String id;

    public SignOutDTO(String id) {
        this.id = id;
    }
    
    public String getId(){return id;}
}
