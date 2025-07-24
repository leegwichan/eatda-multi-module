package eatda.domain.client.oauth;

import eatda.domain.exception.BusinessErrorCode;
import eatda.domain.exception.BusinessException;
import java.io.IOException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient.ResponseSpec.ErrorHandler;

@Component
public class OauthServerErrorHandler implements ErrorHandler {

    @Override
    public void handle(HttpRequest request, ClientHttpResponse response) throws IOException {
        throw new BusinessException(BusinessErrorCode.OAUTH_SERVER_ERROR);
    }
}
