package pagos.payu.infrastructure.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pagos.payu.core.model.common.AntiFraudPreValidationResponse;
import pagos.payu.core.model.entity.Order;
import pagos.payu.core.model.entity.Transaction;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class FraudValidatorService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FraudValidatorService.class);
    private static final String LAMBDA_URL = "http://alb-fraud-validator-47433180.us-east-1.elb.amazonaws.com";

    public AntiFraudPreValidationResponse validateFraud(Order order, Transaction transaction) throws Exception {
        JSONObject innerJson = new JSONObject();
        innerJson.put("orderId", order.getOrderId());
        innerJson.put("transactionId", transaction.getTransactionId().toString());
        innerJson.put("value", transaction.getTotal());
        innerJson.put("accountId", order.getAccountId());
        innerJson.put("merchantId", transaction.getMerchantId());
        innerJson.put("signatureAlgorithm", "SHA256");

        JSONObject requestBodyJson = new JSONObject();
        requestBodyJson.put("body", innerJson.toString());
        String requestBody = requestBodyJson.toString();
        LOGGER.info("Body sent {}", requestBody);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(LAMBDA_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();
        LOGGER.info("Request sent {}", request.toString());

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            LOGGER.error("RESPONSE ERROR {} body {}", response.statusCode(), response.body());
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        AntiFraudPreValidationResponse antiFraudResponse = objectMapper.readValue(response.body(), AntiFraudPreValidationResponse.class);

        return antiFraudResponse;
    }
}