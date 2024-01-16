package com.peponapis.finalproject.security;

/**
 * Class of constants we'll be using for authentication/security/jwt tokens.
 */
public class SecurityConstants {
    public static final long JWT_EXPIRATION = 1000000000000000000L;
    public static final String JWT_SECRET = "ThisIsTheJWTSecretKeyItHasToBeACertainSizeToBeUsed";
}
