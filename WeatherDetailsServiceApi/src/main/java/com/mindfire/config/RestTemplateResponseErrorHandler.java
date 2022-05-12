package com.mindfire.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return new DefaultResponseErrorHandler().hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        final HttpStatus statusCode = response.getStatusCode();
        if (statusCode == null) {
            throw new CustomHttpErrorException("UnknownHttpStatusCodeException", HttpStatus.BAD_REQUEST);
        }
        switch (statusCode.series()) {
            case CLIENT_ERROR:
                HttpHeaders headers = response.getHeaders();
                log.debug("Content-Type : {}", String.valueOf(headers.get("Content-Type")));
                log.debug("Server : {}", String.valueOf(headers.get("Server")));
                throwHttpResponseException(response, statusCode);
                break;
            case SERVER_ERROR:
                throwHttpResponseException(response, statusCode);
                break;
            default:
                throw new CustomHttpErrorException("UnknownHttpStatusCodeException", HttpStatus.BAD_REQUEST);
        }
    }

    private void throwHttpResponseException(ClientHttpResponse response, HttpStatus statusCode) throws IOException {
        log.debug("Status Code : {}", String.valueOf(response.getStatusCode()));
        String responseMessage = new String(getResponseBody(response));
        log.debug(responseMessage);
        throw new CustomHttpErrorException(responseMessage, statusCode);
    }

    protected byte[] getResponseBody(ClientHttpResponse response) {
        try {
            return FileCopyUtils.copyToByteArray(response.getBody());
        } catch (IOException ex) {
            // ignore
        }
        return new byte[0];
    }
}
