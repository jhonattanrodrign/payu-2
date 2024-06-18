package pagos.payu.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pagos.payu.core.model.entity.Amount;

public interface AmountRepository extends JpaRepository<Amount, Long> {
}
