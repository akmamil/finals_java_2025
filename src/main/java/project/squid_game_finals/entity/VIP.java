package project.squid_game_finals.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vip")
public class VIP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String country;

    private BigDecimal wealth;

    @OneToMany(mappedBy = "vip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VipBets> vipBets;

    @ManyToOne
    @JoinColumn(name = "favorite_player_id")
    private Player favoritePlayer;


}

