package pagos.payu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "pagos.payu")
public class PayuApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayuApplication.class, args);
	}

}
