package pagos.payu.core.model.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Embeddable
public class Payer {

	private String name;

	private String document;

	private String documentType;

	private String country;

	private String phone;

	private String address;

	private String email;

	private String cnpj;

	private String personType;

	@Column(name = "pagador_nombre_completo", length = 150)
	@Size(max = 150)
	public String getName() {

		return name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	@Column(name = "pagador_numero_identificacion", length = 20)
	@Size(max = 20)
	public String getDocument() {

		return document;
	}

	public void setDocument(final String document) {

		this.document = document;
	}

	@Column(name = "pagador_tipo_identificacion", length = 20)
	@Size(max = 20)
	public String getDocumentType() {

		return documentType;
	}

	public void setDocumentType(final String documentType) {

		this.documentType = documentType;
	}

	@Column(name = "moneda", length = 150)
	@Size(max = 3)
	public String getCountry() {

		return country;
	}

	public void setCountry(final String country) {

		this.country = country;
	}

	@Column(name = "pagador_telefono", length = 150)
	@Size(max = 25)
	public String getPhone() {

		return phone;
	}

	public void setPhone(final String phone) {

		this.phone = phone;
	}

	@Column(name = "pagador_direccion", length = 150)
	@Size(max = 150)
	public String getAddress() {

		return address;
	}

	public void setAddress(final String address) {

		this.address = address;
	}

	@Column(name = "pagador_email", length = 255)
	@Size(max = 255)
	@Email
	public String getEmail() {

		return email;
	}

	public void setEmail(final String email) {

		this.email = email;
	}

	@Column(name = "pagador_identificacion_empresa", length = 20)
	@Size(max = 20)
	public String getCnpj() {

		return cnpj;
	}

	public void setCnpj(final String cnpj) {

		this.cnpj = cnpj;
	}

	@Column(name = "pagador_tipo_persona", length = 10)
	@Size(max = 10)
	public String getPersonType() {

		return personType;
	}

	public void setPersonType(final String personType) {

		this.personType = personType;
	}
}
