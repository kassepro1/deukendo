package com.kp.securewithjwt.Model;

public class AuthenticationResponse {
    private final String jwttoken;

    public AuthenticationResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getJwttoken() {
        return jwttoken;
    }
}
