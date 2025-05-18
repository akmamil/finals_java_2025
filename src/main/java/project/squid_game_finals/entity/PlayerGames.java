package project.squid_game_finals.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "playergames")
public class PlayerGames {

    @EmbeddedId
    private PlayerGamesId id;

    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Games game;

}



