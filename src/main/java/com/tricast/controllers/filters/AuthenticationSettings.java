package com.tricast.controllers.filters;

public interface AuthenticationSettings {
	static final String SECRET_KEY = "verySecretKey";
    static final String ISSUER = "TricastTanf2018";

    static final String CLAIM_ACCOUNTID_IDENTIFIER = "aid";
    static final String CLAIM_USERNAME_IDENTIFIER = "aud";
    static final String CLAIM_ACCOUNTTYPE_IDENTIFIER = "typ";
}
