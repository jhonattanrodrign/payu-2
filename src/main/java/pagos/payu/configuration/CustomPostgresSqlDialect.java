package pagos.payu.configuration;

import java.sql.Types;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonNodeStringType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.dialect.PostgreSQL9Dialect;

public class CustomPostgresSqlDialect extends PostgreSQL9Dialect {

	/**
	 * Instantiates a new custom postgres sql dialect.
	 */
	public CustomPostgresSqlDialect() {
		this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
		this.registerHibernateType(Types.OTHER, JsonStringType.class.getName());
		this.registerHibernateType(Types.OTHER, JsonBinaryType.class.getName());
		this.registerHibernateType(Types.OTHER, JsonNodeBinaryType.class.getName());
		this.registerHibernateType(Types.OTHER, JsonNodeStringType.class.getName());
	}
}
