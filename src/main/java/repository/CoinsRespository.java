package repository;


import com.habsida.morago.model.entity.Coins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinsRespository extends JpaRepository<Coins, Long> {
}
