package pagos.payu.infrastructure.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import pagos.payu.core.model.entity.Amount;
import pagos.payu.core.model.common.ExtraParameters;
import pagos.payu.core.model.common.PaymentCard;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

public class AuthorizationRequestDto implements Serializable {

    private static final long serialVersionUID = -4872509883344638791L;
    private Integer accountId;

    /**
     * Identifier of the merchant that generates the payment request
     */
    @NotNull
    @Min(0)
    private Integer merchantId;

    /**
     * Identifier of the previously processed order to which this request must be added as a new payment attempt.
     */
    @Min(0)
    private Integer orderId;

    /**
     * Flag that indicates if the transaction must be automatically captured
     */
    private boolean capture;

    /**
     * Reference code of the payment request.
     */
    @NotBlank
    @Length(min = 3, max = 255)
    private String reference;

    /**
     * Defines the amount being collected by the payment request, including additional information like taxes, tax base
     * and administrative fees.
     */
    @NotNull
    @Valid
    private Amount amount;

    /**
     * Payment method being used.
     */
    @NotNull
    private String paymentMethod;

    /**
     * Number of installments in which payments will be processed.
     */
    @Min(0)
    @Max(99)
    private Integer installments;

    /**
     * The date on which the payment request is no longer valid and will be marked as expired. This field applies only
     * to cash payments.
     */
    private Date expirationDate;

    /**
     * Credit Card information
     */
    @Valid
    private PaymentCard paymentCard;

    /**
     * A hash digest that ensures that the request has not been tampered for an external entity.
     */
    private String signature;

    /**
     * Algorithm used to generate the signature ofType the request.
     */
    @Pattern(regexp = "MD5|SHA|SHA256|HMACSHA256")
    private String signatureAlgorithm;

    /**
     * Additional information sent by the merchant to configure the request or used by the merchant at a latter stage.
     */
    @Valid
    private ExtraParameters extraParameters;
}
