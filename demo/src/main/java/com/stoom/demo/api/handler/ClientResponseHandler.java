package com.stoom.demo.api.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;

public class ClientResponseHandler extends DefaultResponseErrorHandler {
	  @Override
	  public void handleError(ClientHttpResponse response) throws IOException {
		  throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Adress invalid");
	  }

}
