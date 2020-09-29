package com.webservice.part2.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class ServiceCallBackHandler implements CallbackHandler {

	Map<String, String> passwordMap = new HashMap<>();

	public ServiceCallBackHandler() {
		passwordMap.put("sumuser", "sumpassword");
		passwordMap.put("myclientkey", "ckpass");
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

		for (Callback callback : callbacks) {
			WSPasswordCallback passwordCallback = (WSPasswordCallback) callback;
			String password = passwordMap.get(passwordCallback.getIdentifier());
			passwordCallback.setPassword(password);
			return;
		}

	}

}
