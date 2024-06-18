package pagos.payu.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pagos.payu.core.model.common.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
