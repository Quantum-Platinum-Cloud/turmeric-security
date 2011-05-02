/*******************************************************************************
 * Copyright (c) 2006-2011 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 *******************************************************************************/

package org.ebayopensource.turmeric.test.services.policyenforcementhandler;

import java.util.ArrayList;
import java.util.List;

import org.ebayopensource.turmeric.authentication.provider.AuthenticationException;
import org.ebayopensource.turmeric.authentication.provider.AuthenticationRequest;
import org.ebayopensource.turmeric.authentication.provider.AuthenticationResponse;
import org.ebayopensource.turmeric.authentication.provider.Authenticator;
import org.ebayopensource.turmeric.security.v1.services.SubjectType;

public class MockAuthenticatePES implements Authenticator{
	private static List<String> s_requiredCredentials;
	public static final String CREDENTIAL_TOKEN = "testtoken";
	private String authnMethod = "TestAuthenticatePES";
	
	static {
		// initialize the static required credential list
		s_requiredCredentials = new ArrayList<String>();
		s_requiredCredentials.add(CREDENTIAL_TOKEN);
	}
 
	
	@Override
	public void initialize() throws AuthenticationException {
		// no init
	}
	
	@Override
	public String getAuthenticationMethod() {
		return authnMethod;
	}
	
	public MockAuthenticatePES(String authnMethod) {
		this.authnMethod = authnMethod;
	}

	
	/**
	 * Authenticate the subject from the incoming request
	 */
	public AuthenticationResponse authenticate(AuthenticationRequest authnRequest) {	
		List<SubjectType> sublist = new ArrayList<SubjectType>();
		SubjectType sub1 = new SubjectType();
		sub1.setValue("validtoken");
		sub1.setDomain("testdomain");
		sublist.add(sub1);
		AuthenticationResponse resp = new AuthenticationResponse();
		// just return success
		resp.setAuthenticationMethod(getAuthenticationMethod());
		resp.setAuthnSubjects(sublist);
		return resp;
	}
	
	/**
	 * Get the required credentials needed by the authenticator
	 */
	public List<String> getRequiredCredentials() {
		return s_requiredCredentials;
	}
}