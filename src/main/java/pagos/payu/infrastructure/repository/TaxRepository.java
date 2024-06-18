package pagos.payu.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pagos.payu.core.model.entity.Tax;

public interface TaxRepository extends JpaRepository<Tax, Long> {
}
