package com.ws.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.ws.model.SumRequest;
import com.ws.model.SumResponse;

@WebService(name = "ISumService")
public interface ISumService {

	@WebResult
	public SumResponse calculateSum(@WebParam SumRequest sumRequest);
}
