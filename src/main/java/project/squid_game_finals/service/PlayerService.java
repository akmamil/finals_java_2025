package project.squid_game_finals.service;

import org.springframework.stereotype.Service;
import project.squid_game_finals.dto.PlayerDTO;
import java.util.List;

@Service
public interface PlayerService {
    List<PlayerDTO> getAllPlayers();
    PlayerDTO getPlayerById(Long id);
    PlayerDTO createPlayer(PlayerDTO playerDTO);
    PlayerDTO updatePlayer(Long id, PlayerDTO playerDTO);
    void deletePlayer(Long id);
}


