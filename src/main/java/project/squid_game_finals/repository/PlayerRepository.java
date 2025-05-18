package project.squid_game_finals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.squid_game_finals.entity.Player;
import project.squid_game_finals.enums.PlayerStatus;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByStatus(PlayerStatus status);
}

