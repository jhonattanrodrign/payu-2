package pagos.payu.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pagos.payu.core.model.common.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String> {
}
