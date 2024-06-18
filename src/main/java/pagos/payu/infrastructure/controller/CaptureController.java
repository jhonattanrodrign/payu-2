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

import pagos.payu.application.services.CaptureServiceImpl;
import pagos.payu.infrastructure.dto.capture.CaptureRequest;
import pagos.payu.infrastructure.dto.capture.CaptureResponse;
import pagos.payu.core.model.common.TransactionResponse;

@RestController
@RequestMapping(path = "/payment/capture",
		consumes = { MediaType.APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE })
@RequiredArgsConstructor
public class CaptureController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CaptureController.class);

	private final CaptureServiceImpl useCaseCapture;

	/**
	 * Process a capture request
	 *
	 * @param captureRequest The capture request to process
	 * @return A capture response for the submitted request
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CaptureResponse> doCapture(@NotNull @Valid @RequestBody final CaptureRequest captureRequest) {

		try {

			final TransactionResponse transactionResponse = useCaseCapture.processRequest(captureRequest.getOrderId(),
																		   captureRequest.getAuthorizationId(), captureRequest);

			return new ResponseEntity<>(new CaptureResponse(transactionResponse), HttpStatus.OK);
		} catch (final RuntimeException exception) {

			LOGGER.error("Error processing capture request. Error Message: [" + exception.getMessage()
								 + "]  Response: INTERNAL_SERVER_ERROR. CaptureRequest ["
								 + getCaptureRequestDescription(captureRequest) + "]", exception);

			return new ResponseEntity<>((CaptureResponse) null, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Creates String describing the given capture requests
	 *
	 * @param captureRequest The payment request to buildDescription
	 * @return A string containing the major capture request fields
	 */
	private String getCaptureRequestDescription(final CaptureRequest captureRequest) {

		if (captureRequest == null) {
			return StringUtils.EMPTY;
		}

		return new StringBuilder(150)
				.append("AuthorizationId: [").append(captureRequest.getAuthorizationId())
				.append("] OrderId: [").append(captureRequest.getOrderId())
				.append("] Value: [").append(captureRequest.getValue()).append("]")
				.toString();
	}
}

