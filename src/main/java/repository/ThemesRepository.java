package repository;

import com.habsida.morago.model.entity.Themes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemesRepository extends JpaRepository<Themes, Long> {
}
