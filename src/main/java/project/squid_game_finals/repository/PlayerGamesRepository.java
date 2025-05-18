package project.squid_game_finals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.squid_game_finals.entity.PlayerGames;
import project.squid_game_finals.entity.PlayerGamesId;

@Repository
public interface PlayerGamesRepository extends JpaRepository<PlayerGames, PlayerGamesId> {
}
