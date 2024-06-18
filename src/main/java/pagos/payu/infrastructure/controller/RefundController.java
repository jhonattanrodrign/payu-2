package pagos.payu.infrastructure.controller;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import pagos.payu.core.UseCaseRefund;
import pagos.payu.core.model.common.TransactionResponse;
import pagos.payu.infrastructure.dto.refund.RefundRequest;
import pagos.payu.infrastructure.dto.refund.RefundResponse;

@RestController
@RequestMapping(path = "/refund",
		consumes = { MediaType.APPLICATION_JSON_VALUE },
		produces = {MediaType.APPLICATION_JSON_VALUE })
@RequiredArgsConstructor
public class RefundController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RefundController.class);

	private final UseCaseRefund useCaseRefund;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<RefundResponse> doRefund(@NotNull @Valid @RequestBody final RefundRequest refundRequest) {

		try {

			final TransactionResponse transactionResponse = useCaseRefund.processRequest(refundRequest.getOrderId(), refundRequest.getCaptureId(),
																						 refundRequest);
			return new ResponseEntity<>(new RefundResponse(transactionResponse), HttpStatus.OK);
		} catch (final RuntimeException exception) {

			LOGGER.error("Error processing refund request. Error Message: [" + exception.getMessage()
								 + "]  Response: INTERNAL_SERVER_ERROR. AuthorizationRequest ["
								 + getRefundRequestDescription(refundRequest) + "]", exception);

			return new ResponseEntity<>((RefundResponse) null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private String getRefundRequestDescription(final RefundRequest refundRequest) {

		if (refundRequest == null) {
			return StringUtils.EMPTY;
		}

		final StringBuilder description = new StringBuilder(150)
				.append("RefundId: [").append(refundRequest.getCaptureId())
				.append("] OrderId: [").append(refundRequest.getOrderId())
				.append("] Value: [").append(refundRequest.getValue()).append("]");

		return description.toString();
	}
}
