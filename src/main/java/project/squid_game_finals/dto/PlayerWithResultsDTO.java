package project.squid_game_finals.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.squid_game_finals.entity.Player;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerWithResultsDTO {
    private Long id;
    private String name;
    private BigDecimal balance;
    private List<RoundResultDTO> roundResults;

    public PlayerWithResultsDTO(Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.balance = player.getBalance();
        this.roundResults = player.getRoundResults()
                .stream()
                .map(RoundResultDTO::new)
                .collect(Collectors.toList());
    }
}

