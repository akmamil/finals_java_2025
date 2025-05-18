package project.squid_game_finals.dto;

import lombok.Getter;
import lombok.Setter;
import project.squid_game_finals.entity.Player;
import project.squid_game_finals.enums.PlayerStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PlayerDTO {
    private Long id;
    private String name;
    private int age;
    private PlayerStatus status;
    private BigDecimal balance;
    private LocalDateTime registrationDate;

    public PlayerDTO(Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.age = player.getAge();
        this.status = player.getStatus();
        this.balance = player.getBalance();
        this.registrationDate = player.getRegistrationDate();
    }
}


