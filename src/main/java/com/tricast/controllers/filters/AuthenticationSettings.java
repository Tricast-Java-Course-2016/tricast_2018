package com.tricast.controllers.filters;

public interface AuthenticationSettings {
	static final String SECRET_KEY = "verySecretKey";
    static final String ISSUER = "TricastTanf2018";
	static final long EXPIRY_TIME_IN_SEC = 60L*10L;

    static final String ACCOUNTID = "aid";
    static final String USERNAME = "aud";
    static final String ACCOUNTTYPE = "typ";
}
