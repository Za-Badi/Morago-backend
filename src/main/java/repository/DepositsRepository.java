package repository;

import com.habsida.morago.entities.Deposits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositsRepository extends JpaRepository<Deposits, Long> {
}
