package repository;

import com.habsida.morago.entities.Withdrawals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalRepository extends JpaRepository<Withdrawals, Long> {
}
