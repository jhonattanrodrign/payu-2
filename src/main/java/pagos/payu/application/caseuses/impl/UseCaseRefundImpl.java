package pagos.payu.application.caseuses.impl;


import lombok.extern.slf4j.Slf4j;
import pagos.payu.application.caseuses.UseCaseRefund;
import pagos.payu.core.model.common.AntiFraudPreValidationResponse;
import pagos.payu.core.model.common.AntiFraudValidationState;
import pagos.payu.core.model.common.TransactionResponse;
import pagos.payu.core.model.entity.Transaction;
import pagos.payu.infrastructure.dto.antifraud.AntifraudRequest;
import pagos.payu.infrastructure.dto.capture.CaptureRequest;
import pagos.payu.infrastructure.dto.capture.CaptureResponse;
import pagos.payu.infrastructure.dto.refund.RefundRequest;
import pagos.payu.infrastructure.dto.refund.RefundResponse;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
public class UseCaseRefundImpl implements UseCaseRefund {

    @Override
    public TransactionResponse processRequest(Transaction refundTransaction, RefundRequest refundRequest) {
        if(null == refundTransaction.getFraudStatus()) {
            AntiFraudPreValidationResponse antifraudResponse = createAntifraudRequest(refundTransaction);
            refundTransaction.setFraudStatus(antifraudResponse.getDecision());
        }
        return sendAuthorizationRequestAcquirerNetwork(refundRequest, refundTransaction);
    }

    private TransactionResponse sendAuthorizationRequestAcquirerNetwork(final RefundRequest refundRequest, Transaction refundTransaction) {

        RefundResponse refundResponse = sendAuthorizationRequestToAcquirerNetwork(refundRequest, refundTransaction);
        refundResponse.setTransactionId(refundTransaction.getTransactionId().toString());
        refundResponse.setOrderId(refundTransaction.getOrderId());
        return evaluateAuthorizationResponse(refundResponse, refundRequest);
    }

    private TransactionResponse evaluateAuthorizationResponse(final RefundResponse refundResponse, RefundRequest refundRequest) {
        if(refundResponse != null && "0".equals(refundResponse.getTraceabilityCode())
                && "APPROVED".equals(refundResponse.getCaptureCode())){
            return TransactionResponse.builder()
                    .authorizationCode("0")
                    .orderId(refundResponse.getOrderId())
                    .paymentNetworkResponseCode("APPROVED")
                    .responseCode("APROBADA")
                    .state("REFUNDED")
                    .transactionId(refundResponse.getTransactionId())
                    .paymentNetworkResponseErrorMessage(null)
                    .traceabilityCode("1")
                    .operationDate(new Date())
                    .pendingReason(null)
                    .travelAgencyAuthorizationCode("1")
                    .build();
        }else{
            return TransactionResponse.builder()
                    .authorizationCode("1")
                    .orderId(refundResponse.getOrderId())
                    .paymentNetworkResponseCode("REJECTED")
                    .responseCode("Rechazado")
                    .state("DECLINED")
                    .transactionId(refundResponse.getTransactionId())
                    .paymentNetworkResponseErrorMessage("Balance in 0")
                    .traceabilityCode("2")
                    .operationDate(new Date())
                    .pendingReason(null)
                    .travelAgencyAuthorizationCode("2")
                    .build();
        }
    }

    private RefundResponse sendAuthorizationRequestToAcquirerNetwork(final RefundRequest refundRequest, final Transaction refundTransaction) {

        if(refundTransaction.getTotal().compareTo(new BigDecimal(100000))>0){
            return RefundResponse.builder()
                    .captureCode("DECLINED")
                    .traceabilityCode("1")
                    .build();
        }
        return RefundResponse.builder()
                .captureCode("APPROVED")
                .traceabilityCode("0")
                .build();
    }

    private AntiFraudPreValidationResponse createAntifraudRequest(final Transaction transaction) {

        AntifraudRequest request= new AntifraudRequest();
        request.setAccountId(1);
        request.setValue(BigDecimal.ONE);
        request.setMerchantId(transaction.getMerchantId());
        request.setTransactionId(transaction.getTransactionId());
        request.setOrderId(transaction.getOrderId());

        //todo send request to antifraud validator and separate it in this provider
        return antiFraudValidator(request);

    }

    private AntiFraudPreValidationResponse antiFraudValidator(final AntifraudRequest request) {

        return AntiFraudPreValidationResponse.builder()
                .antifraudValidationState(AntiFraudValidationState.VERIFIED)
                .codeResponse("0")
                .creditCardCountry("CO")
                .creditCardIssuerBank("BANCOLOMBIA")
                .decision("APPROVED")
                .errorCode(null)
                .errorMessage(null)
                .id(request.getTransactionId())
                .orderId(request.getOrderId())
                .processingTime("0000012")
                .providerAntiFraud("FEEDZAI")
                .build();

    }
}
