package com.peponapis.finalproject.security;

/**
 * Class of constants we'll be using for authentication/security/jwt tokens.
 */
public class SecurityConstants {
    public static final long JWT_EXPIRATION = 100000000;
    public static final String JWT_SECRET = "ThisIsTheJWTSecretKeyItHasToBeACertainSizeToBeUsed";
}
