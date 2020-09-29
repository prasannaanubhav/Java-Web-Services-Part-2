package com.webservice.part2.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

import com.ws.service.ISumService;
import com.ws.service.ISumServiceService;
import com.ws.service.SumRequest;
import com.ws.service.SumResponse;

public class SumServiceClient {

	public static void main(String[] args) throws MalformedURLException {

		ISumServiceService service = new ISumServiceService(
				new URL("http://localhost:8080/JavaWebServicesPart2/services/sum?wsdl"));
		ISumService iSumServicePort = service.getISumServicePort();
		Client client = ClientProxy.getClient(iSumServicePort);
		Endpoint endpoint = client.getEndpoint();

		Map<String, Object> outProps = new HashMap<>();
		outProps.put(WSHandlerConstants.ACTION, "UsernameToken Encrypt Signature Timestamp");
		// authentication confidentiality integrity repudiation;

//	 	when you performing encryption by parts then must follow the sequence for action as mentioned here
//		UsernameToken Signature Encrypt Timestamp

		outProps.put(WSHandlerConstants.USER, "sumuser");
		outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
		outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ServiceCallBackHandler.class.getName());

		outProps.put(WSHandlerConstants.ENCRYPTION_USER, "myservicekey");
		outProps.put(WSHandlerConstants.ENC_PROP_FILE, "etc/clientKeystore.properties");

		outProps.put(WSHandlerConstants.SIGNATURE_USER, "myclientkey");
		outProps.put(WSHandlerConstants.SIG_PROP_FILE, "etc/clientKeystore.properties");

		outProps.put("timeToLive", "30");

		WSS4JOutInterceptor wss4jOutInterceptor = new WSS4JOutInterceptor(outProps);
		endpoint.getOutInterceptors().add(wss4jOutInterceptor);

		Map<String, Object> inProps = new HashMap<>();
		inProps.put(WSHandlerConstants.ACTION, "Encrypt Signature Timestamp");
		inProps.put(WSHandlerConstants.DEC_PROP_FILE, "etc/clientKeystore.properties");
		inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ServiceCallBackHandler.class.getName());
		inProps.put(WSHandlerConstants.SIG_PROP_FILE, "etc/clientKeystore.properties");

		WSS4JInInterceptor wss4jInInterceptor = new WSS4JInInterceptor(inProps);
		endpoint.getInInterceptors().add(wss4jInInterceptor);

		SumRequest sumRequest = new SumRequest();
		sumRequest.setNum1(10);
		sumRequest.setNum2(20);
		SumResponse response = iSumServicePort.calculateSum(sumRequest);
		System.out.println(response.getResult());

	}
}
