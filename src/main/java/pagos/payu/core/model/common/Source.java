package pagos.payu.core.model.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.Length;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Source {

	/**
	 * The session identifier of the device where the transaction was performed from.
	 */
	@Length(max = 255)
	private String deviceSessionId;

	/**
	 * The IP address of the device where the transaction was performed from.
	 */
	@Length(max = 39)
	private String ipAddress;

	/**
	 * The cookie stored on the device where the transaction was performed from.
	 */
	@Length(max = 255)
	private String cookie;

	/**
	 * The user agent of the browser from which the transaction was performed.
	 */
	@Length(max = 1204)
	private String userAgent;

	/**
	 * Private constructor, required by Jackson
	 */
	private Source() {

		super();
	}

	public Source(final Builder builder) {

		this.deviceSessionId = builder.deviceSessionId;
		this.ipAddress = builder.ipAddress;
		this.cookie = builder.cookie;
		this.userAgent = builder.userAgent;
	}

	public String getDeviceSessionId() {

		return deviceSessionId;
	}

	public String getIpAddress() {

		return ipAddress;
	}

	public String getCookie() {

		return cookie;
	}

	public String getUserAgent() {

		return userAgent;
	}

	public static Builder withDeviceSessionId(final String deviceSessionId) {

		return new Builder().withDeviceSessionId(deviceSessionId);
	}

	/**
	 * Builder class for {@linkplain Source} entities
	 *
	 * @author Manuel E. Vieda (manuel.vieda@payulatam.com)
	 */
	public static class Builder {

		/**
		 * The session identifier of the device where the transaction was performed from.
		 */
		private String deviceSessionId;

		/**
		 * The IP address of the device where the transaction was performed from.
		 */
		private String ipAddress;

		/**
		 * The cookie stored on the device where the transaction was performed from.
		 */
		private String cookie;

		/**
		 * The user agent of the browser from which the transaction was performed.
		 */
		private String userAgent;

		public Builder withDeviceSessionId(final String deviceSessionId) {

			this.deviceSessionId = deviceSessionId;
			return this;
		}

		public Builder withIpAddress(final String ipAddress) {

			this.ipAddress = ipAddress;
			return this;
		}

		public Builder withCookie(final String cookie) {

			this.cookie = cookie;
			return this;
		}

		public Builder withUserAgent(final String userAgent) {

			this.userAgent = userAgent;
			return this;
		}
	}

}
