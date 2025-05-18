package project.squid_game_finals.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vipbets")
public class VipBets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vip_id")
    private VIP vip;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Games game;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;

    @Column(name = "bet_amount", nullable = false)
    private BigDecimal betAmount;

}

