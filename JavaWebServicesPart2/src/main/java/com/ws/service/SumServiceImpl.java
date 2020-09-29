package com.ws.service;

import com.ws.model.SumRequest;
import com.ws.model.SumResponse;

public class SumServiceImpl implements ISumService {

	@Override
	public SumResponse calculateSum(SumRequest sumRequest) {

		int result = sumRequest.getNum1() + sumRequest.getNum2();
		SumResponse sumResponse = new SumResponse();
		sumResponse.setResult(result);
		return sumResponse;
	}

}
