package project.squid_game_finals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.squid_game_finals.entity.RoundResult;
import project.squid_game_finals.enums.RoundResultStatus;

import java.util.List;

@Repository
public interface RoundResultRepository extends JpaRepository<RoundResult, Long> {
    List<RoundResult> findByResult(RoundResultStatus result);
}

