package project.squid_game_finals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.squid_game_finals.entity.VipBets;

@Repository
public interface VipBetsRepository extends JpaRepository<VipBets, Long> {
}

