package project.squid_game_finals.entity;

import jakarta.persistence.*;
import lombok.Data;
import project.squid_game_finals.enums.GuardRank;

@Entity
@Data
@Table(name = "guard")
public class Guard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private GuardRank rank;

    @ManyToOne
    @JoinColumn(name = "assigned_game_id")
    private Games assignedGame;

}

