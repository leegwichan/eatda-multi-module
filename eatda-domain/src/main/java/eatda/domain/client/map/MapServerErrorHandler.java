package eatda.domain.client.map;

import eatda.domain.exception.BusinessErrorCode;
import eatda.domain.exception.BusinessException;
import java.io.IOException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient.ResponseSpec.ErrorHandler;

public class MapServerErrorHandler implements ErrorHandler {

    @Override
    public void handle(HttpRequest request, ClientHttpResponse response) throws IOException {
        throw new BusinessException(BusinessErrorCode.MAP_SERVER_ERROR);
    }
}
