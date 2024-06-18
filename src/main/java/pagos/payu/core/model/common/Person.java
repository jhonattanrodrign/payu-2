package pagos.payu.core.model.common;

import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.Length;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Person {

	/**
	 * Full name of the person (Includes first and last name)
	 */
	@Length(max = 150)
	private String fullName;

	/**
	 * Description of the national identification number. This value depends on the country of the person.
	 */
	@Length(max = 6)
	private String identificationType;

	/**
	 * Identification Number.
	 */
	@Length(max = 20)
	private String identificationNumber;

	/**
	 * The person's gender. The gender is useful for antifraud validation process.
	 */
	@Pattern(regexp = "M|m|F|f|O|o")
	@Length(max = 1)
	private String gender;

	/**
	 * The date on which a person was born. This date is useful for antifraud validation process.
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date birthdate;

	/**
	 * The person email address
	 */
	@Length(max = 255)
	private String email;

	/**
	 * The person contact phone
	 */
	@Length(max = 20)
	private String phone;

	/**
	 * The person type. (Can be natural or legal)
	 */
	@Length(max = 10)
	private String type;

	/**
	 * The person business Name (Only valid if the person type is legal)
	 */
	@Length(max = 150)
	private String businessName;

	/**
	 * CNPJ (Short for Cadastro Nacional da Pessoa Jurídica in Portuguese, or National Registry of Legal Entities),
	 * is an identification number issued to Brazilian companies by the Secretariat of the Federal Revenue of Brazil
	 * (in Portuguese, Secretaria da Receita Federal).
	 */
	// @CNPJ FIXME Add CNPJ validation after fixing PPS-PaymentsAPI issue
	@Length(max = 20)
	private String cnpj;

	/**
	 * Identifier of the person at the merchant's systems
	 */
	@Length(max = 100)
	private String merchantPersonId;

	/**
	 * The person address
	 */
	@Valid
	private String address;

	/**
	 * Default constructor required by Jackson
	 */
	private Person() {

		super();
	}

	/**
	 * Person constructor from builder
	 *
	 * @param builder Person builder
	 */
	public Person(final Builder builder) {

		this.fullName = builder.fullName;
		this.identificationType = builder.identificationType;
		this.identificationNumber = builder.identificationNumber;
		this.gender = builder.gender;
		this.birthdate = builder.birthdate;
		this.email = builder.email;
		this.phone = builder.phone;
		this.type = builder.personType;
		this.businessName = builder.businessName;
		this.cnpj = builder.cnpj;
		this.merchantPersonId = builder.merchantPersonId;
		this.address = builder.address;
	}

	/**
	 * Returns the full name
	 *
	 * @return the full name
	 */
	public String getFullName() {

		return fullName;
	}

	/**
	 * Returns the email address
	 *
	 * @return the email address
	 */
	public String getEmail() {

		return email;
	}

	/**
	 * Returns the identification document type
	 *
	 * @return The identification document type
	 */
	public String getIdentificationType() {

		return identificationType;
	}

	/**
	 * Returns the identification document number
	 *
	 * @return the identification document number
	 */
	public String getIdentificationNumber() {

		return identificationNumber;
	}

	/**
	 * Returns the person's gender
	 *
	 * @return The person's gender
	 */
	public String getGender() {

		return gender;
	}

	/**
	 * Returns the person's birthdate
	 *
	 * @return The person's birthdate
	 */
	public Date getBirthdate() {

		return birthdate;
	}

	/**
	 * Returns the phone
	 *
	 * @return the phone
	 */
	public String getPhone() {

		return phone;
	}

	/**
	 * Returns the type
	 *
	 * @return the type
	 */
	public String getType() {

		return type;
	}

	/**
	 * Returns the businessName
	 *
	 * @return the businessName
	 */
	public String getBusinessName() {

		return businessName;
	}

	/**
	 * Returns the cnpj
	 *
	 * @return the cnpj
	 */
	public String getCnpj() {

		return cnpj;
	}

	/**
	 * Returns the merchant person identifier
	 *
	 * @return The merchant person identifier
	 */
	public String getMerchantPersonId() {

		return merchantPersonId;
	}

	/**
	 * Returns the address
	 *
	 * @return the address
	 */
	public String getAddress() {

		return address;
	}

	/**
	 * Creates a new {@linkplain Builder Person Builder} instance configured wiht the full name
	 *
	 * @param fullName The full name to set
	 */
	public static Builder wihtName(final String fullName) {

		return new Builder().withFullName(fullName);
	}

	/**
	 * Builder class for {@linkplain Person} entities
	 *
	 * @author Manuel E. Vieda (manuel.vieda@payulatam.com)
	 */
	public static class Builder {

		/**
		 * Full name of the person (Includes first and last name)
		 */
		private String fullName;

		/**
		 * Description of the national identification type.
		 */
		private String identificationType;

		/**
		 * Identification Number.
		 */
		private String identificationNumber;

		/**
		 * The person's gender. The gender is useful for antifraud validation process.
		 */
		private String gender;

		/**
		 * The date on which a person was born. This date is useful for antifraud validation process.
		 */
		private Date birthdate;

		/**
		 * The person email address
		 */
		private String email;

		/**
		 * The person contact phone
		 */
		private String phone;

		/**
		 * The person type. (Can be natural or legal)
		 */
		private String personType;

		/**
		 * The person business Name (Only valid if the person type is legal)
		 */
		private String businessName;

		/**
		 * CNPJ
		 */
		private String cnpj;

		/**
		 * Identifier of the person at the merchant's systems
		 */
		private String merchantPersonId;

		/**
		 * The person address
		 */
		private String address;

		/**
		 * Set the full name of the person
		 *
		 * @param fullName The full name to set
		 * @return This builder
		 * @see Person#fullName
		 */
		public Builder withFullName(final String fullName) {

			this.fullName = fullName;
			return this;
		}

		/**
		 * Sets the description of the national identification
		 *
		 * @param identificationType The national identification type to set
		 * @return This builder
		 * @see Person#identificationType
		 */
		public Builder withIdentificationType(final String identificationType) {

			this.identificationType = identificationType;
			return this;
		}

		/**
		 * Sets the identification number
		 *
		 * @param identificationNumber The identification number to set
		 * @return This builder
		 * @see Person#identificationNumber
		 */
		public Builder withIdentificationNumber(final String identificationNumber) {

			this.identificationNumber = identificationNumber;
			return this;
		}

		/**
		 * Sets the email address
		 *
		 * @param email The email address to set
		 * @return This builder
		 * @see Person#email
		 */
		public Builder withEmail(final String email) {

			this.email = email;
			return this;
		}

		/**
		 * Sets the contact phone
		 *
		 * @param contactPhone The contact phone number to set
		 * @return This builder
		 * @see Person#phone
		 */
		public Builder withPhone(final String contactPhone) {

			this.phone = contactPhone;
			return this;
		}

		/**
		 * Sets the person type
		 *
		 * @param personType The person type to set
		 * @return This builder
		 * @see Person#type
		 */
		public Builder withPersonType(final String personType) {

			this.personType = personType;
			return this;
		}

		/**
		 * Sets the business name
		 *
		 * @param businessName The business name to set
		 * @return This builder
		 * @see Person#businessName
		 */
		public Builder withBusinessName(final String businessName) {

			this.businessName = businessName;
			return this;
		}

		/**
		 * Sets the CNPJ
		 *
		 * @param cnpj The CNPJ to set
		 * @return This builder
		 * @see Person#cnpj
		 */
		public Builder withCnpj(final String cnpj) {

			this.cnpj = cnpj;
			return this;
		}

		/**
		 * Sets the address
		 *
		 * @param address The address to set
		 * @return This builder
		 * @see Person#address
		 */
		public Builder withAddress(final String address) {

			this.address = address;
			return this;
		}

		/**
		 * Return the person's gender
		 *
		 * @param gender The person's gender to set
		 * @return This builder
		 * @see Person#gender
		 */
		public Builder withGender(final String gender) {

			this.gender = gender;
			return this;
		}

		/**
		 * Sets the person's birth date
		 *
		 * @param birthdate The person's birth date to set
		 * @return This builder
		 * @see Person#birthdate
		 */
		public Builder withBirthdate(final Date birthdate) {

			this.birthdate = birthdate;
			return this;
		}

		/**
		 * Sets the person identifier at the merchants system
		 *
		 * @param merchantPersonId The merchant person identifier
		 * @return This builder
		 * @see Person#merchantPersonId
		 */
		public Builder withMerchantPersonId(final String merchantPersonId) {

			this.merchantPersonId = merchantPersonId;
			return this;
		}

		/**
		 * Creates and return a new {@linkplain Person} instance configured with the given values
		 *
		 * @return A new {@linkplain Person} instance
		 */
		public Person build() {

			return new Person(this);
		}
	}

}
