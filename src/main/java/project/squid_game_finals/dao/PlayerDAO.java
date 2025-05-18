package project.squid_game_finals.dao;

import project.squid_game_finals.entity.Player;
import project.squid_game_finals.enums.PlayerStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PlayerDAO {
    void save(Player player);
    Optional<Player> findById(Long id);
    Player findByIdWithRoundResults(Long id);
    List<Player> findAll();
    void update(Player player);
    void delete(Long id);
    List<Player> findByStatus(PlayerStatus status);
    List<Player> findAllWithNativeSQL();
    List<Player> findByBalanceGreaterThan(BigDecimal amount);
    Long countByStatus(PlayerStatus status);
}
