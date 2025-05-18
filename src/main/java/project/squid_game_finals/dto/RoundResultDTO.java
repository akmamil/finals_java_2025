package project.squid_game_finals.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.squid_game_finals.entity.RoundResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoundResultDTO {
    private Long id;
    private String result;

    public RoundResultDTO(RoundResult rr) {
        this.id = rr.getId();
        this.result = rr.getResult().name(); // или другой нужный атрибут
    }
}

