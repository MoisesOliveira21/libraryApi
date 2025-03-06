package com.example.libraryapi;

public class CustomAuthenticationToken extends UserName {
    public CustomAuthenticationToken(String username, String password) {
        super(username, password, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public CustomAuthenticationToken(Object principal, Object credentials) {
        super();
    }
}
