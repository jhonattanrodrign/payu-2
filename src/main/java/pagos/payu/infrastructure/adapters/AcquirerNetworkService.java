package pagos.payu.infrastructure.adapters;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.infrastructure.dto.authorization.AuthorizationResponse;
import pagos.payu.infrastructure.dto.capture.CaptureRequest;
import pagos.payu.infrastructure.dto.capture.CaptureResponse;
import pagos.payu.infrastructure.dto.refund.RefundRequest;
import pagos.payu.infrastructure.dto.refund.RefundResponse;

@Service
public class AcquirerNetworkService {

    @Autowired
    private RestTemplate restTemplate;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FraudValidatorService.class);
    private static final String LAMBDA_URL_AUTHORIZATION = "http://load-balancer-bank-2134635314.us-east-1.elb.amazonaws.com";

    public AuthorizationResponse sendAuthorizationRequestToAcquirerNetwork(AuthorizationRequest authorizationRequest) {
        String url = "https://acquirer-network.example.com/authorize"; // URL de la red externa
        try {
            return restTemplate.postForObject(url, authorizationRequest, AuthorizationResponse.class);
        } catch (RestClientException e) {
            throw new RuntimeException("Error al comunicarse con la red del adquirente", e);
        }
    }

    public CaptureResponse sendCaptureRequestToAcquirerNetwork(CaptureRequest captureRequest) {
        String url = "https://acquirer-network.example.com/capture"; // URL de la red externa
        try {
            return restTemplate.postForObject(url, captureRequest, CaptureResponse.class);
        } catch (RestClientException e) {
            throw new RuntimeException("Error al comunicarse con la red del adquirente", e);
        }
    }

    public RefundResponse sendRefundRequestToAcquirerNetwork(RefundRequest refundRequest) {
        String url = "https://acquirer-network.example.com/refund"; // URL de la red externa
        try {
            return restTemplate.postForObject(url, refundRequest, RefundResponse.class);
        } catch (RestClientException e) {
            throw new RuntimeException("Error al comunicarse con la red del adquirente", e);
        }
    }

}
