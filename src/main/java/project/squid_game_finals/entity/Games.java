package project.squid_game_finals.entity;

import jakarta.persistence.*;
import lombok.*;
import project.squid_game_finals.enums.GameStatus;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "games")
public class Games {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    private String description;

    @Column(name = "game_date", nullable = false)
    private LocalDate gameDate;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Round> rounds;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayerGames> playerGames;
}

