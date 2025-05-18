package project.squid_game_finals.entity;
import jakarta.persistence.*;
import lombok.*;
import project.squid_game_finals.enums.RoundResultStatus;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roundresult")
public class RoundResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "round_id", nullable = false)
    private Round round;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoundResultStatus result;

}

