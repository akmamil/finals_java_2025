package project.squid_game_finals.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import project.squid_game_finals.entity.Player;
import project.squid_game_finals.enums.PlayerStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PlayerDTO {
    private Long id;

    @NotBlank(message = "Name must not be blank")
    private String name;

    @Min(value = 0, message = "Age must be non-negative")
    private int age;

    @NotNull(message = "Status must not be null")
    private PlayerStatus status;

    @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be non-negative")
    private BigDecimal balance;

    private LocalDateTime registrationDate;

    public PlayerDTO() {}

    public PlayerDTO(Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.age = player.getAge();
        this.status = player.getStatus();
        this.balance = player.getBalance();
        this.registrationDate = player.getRegistrationDate();
    }

    public Player toEntity() {
        Player player = new Player();
        player.setId(this.id);
        player.setName(this.name);
        player.setAge(this.age);
        player.setStatus(this.status);
        player.setBalance(this.balance);
        player.setRegistrationDate(this.registrationDate); // можно не ставить, если ты задаёшь её в сервисе
        return player;
    }

}


