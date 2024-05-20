package repository;

import com.habsida.morago.model.entity.FrequentlyAskedQuestions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrequentlyAskedQuestionsRespository extends JpaRepository<FrequentlyAskedQuestions, Long> {
}
