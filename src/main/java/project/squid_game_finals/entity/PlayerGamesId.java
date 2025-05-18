package project.squid_game_finals.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class PlayerGamesId implements Serializable {

    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "game_id")
    private Long gameId;

    public PlayerGamesId() {}

    public PlayerGamesId(Long playerId, Long gameId) {
        this.playerId = playerId;
        this.gameId = gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerGamesId that)) return false;  // вот так — паттерн переменная
        return Objects.equals(playerId, that.playerId) &&
                Objects.equals(gameId, that.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, gameId);
    }
}
