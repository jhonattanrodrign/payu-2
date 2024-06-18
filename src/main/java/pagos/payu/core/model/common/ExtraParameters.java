package pagos.payu.core.model.common;


import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ExtraParameters {

	public static final int EXTRA_PARAMETER_KEY_MAX_LENGTH = 64;

	public static final int EXTRA_PARAMETER_VALUE_MAX_LENGTH = 255;

	/**
	 * Identifier of the merchant at the antifraud system
	 */
	private String antifraudMerchantId;

	/**
	 * Map containing all the key-value information sent by the merchant. The key and the value must be String
	 * values, otherwise will be ignored.
	 */
	private Map<String, String> properties = new HashMap<>();

	/**
	 * Returns the antifraud merchant identifier
	 *
	 * @return The antifraud merchant identifier
	 */
	public String getAntifraudMerchantId() {

		return antifraudMerchantId;
	}

	/**
	 * Returns all the extra parameters sent by the merchant
	 *
	 * @return A map containing all the extra parameters sent by the merchant
	 */
	@JsonAnyGetter
	public Map<String, String> getProperties() {

		return properties;
	}

	/**
	 * Sets the antifraud merchant identifier
	 *
	 * @param antifraudMerchantId The antifraud merchant identifier to set
	 */
	public void setAntifraudMerchantId(String antifraudMerchantId) {

		this.antifraudMerchantId = antifraudMerchantId;
	}

	/**
	 * Add a new extra parameter to the request
	 *
	 * @param key The key of the extra parameter
	 * @param value The value of the extra parameter
	 */
	@JsonAnySetter
	public void addProperty(final String key, final String value) {

		if (StringUtils.isNotBlank(key) && key.length() <= EXTRA_PARAMETER_KEY_MAX_LENGTH) {
			properties.put(key, StringUtils.abbreviate(value, EXTRA_PARAMETER_VALUE_MAX_LENGTH));
		}

	}


}
