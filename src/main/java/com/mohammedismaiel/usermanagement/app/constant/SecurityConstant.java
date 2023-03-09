package com.mohammedismaiel.usermanagement.app.constant;

public class SecurityConstant {
    public static final String MINE = "Mohammed_Ismaiel";
    public static final long EXPIRATION_TIME = 432_000_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token can not be verified";
    public static final String ADMINISTRATION = "USER MANAGEMENT PORTAL";
    public static final String AUTHORITIES = "Authorities";
    public static final String FORBIDDEN_MESSAGE = "YOU NEED TO LOGIN TO ACCESS THIS CONTENT";
    public static final String ACCESS_DENIED_MESSAGE = "YOU DON'T HAVE PERMISION TO THIS CONTENT";
    public static final String OPTIONS_HTTP_METHOUD = "YOU DON'T HAVE PERMISION TO THIS CONTENT";
    public static final String[] PUBLIC_URLS = { "/user/login", "/user/register", "/user/home",
            "/user/resetpassword/**", "/user/image/**" };
    // public static final String[] PUBLIC_URLS = {"**"};
}
