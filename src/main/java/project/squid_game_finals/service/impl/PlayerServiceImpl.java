package project.squid_game_finals.service.impl;

import lombok.RequiredArgsConstructor;
import project.squid_game_finals.dto.PlayerDTO;
import project.squid_game_finals.entity.Player;
import project.squid_game_finals.exception.ResourceNotFoundException;
import project.squid_game_finals.repository.PlayerRepository;
import project.squid_game_finals.service.PlayerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDTO getPlayerById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id " + id));
        return new PlayerDTO(player);
    }

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        Player player = playerDTO.toEntity();
        player.setRegistrationDate(LocalDateTime.now());
        Player saved = playerRepository.save(player);
        return new PlayerDTO(saved);
    }

    @Override
    public PlayerDTO updatePlayer(Long id, PlayerDTO dto) {
        return playerRepository.findById(id)
                .map(player -> {
                    player.setName(dto.getName());
                    player.setAge(dto.getAge());
                    player.setStatus(dto.getStatus());
                    player.setBalance(dto.getBalance());
                    return new PlayerDTO(playerRepository.save(player));
                }).orElseThrow(() -> new ResourceNotFoundException("Player not found with id " + id));
    }

    @Override
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}