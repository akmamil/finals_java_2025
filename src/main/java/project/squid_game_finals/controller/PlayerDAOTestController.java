package project.squid_game_finals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.squid_game_finals.dao.PlayerDAO;
import project.squid_game_finals.dto.PlayerDTO;
import project.squid_game_finals.dto.PlayerWithResultsDTO;
import project.squid_game_finals.entity.Player;
import project.squid_game_finals.enums.PlayerStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dao/players")
public class PlayerDAOTestController {

    private final PlayerDAO playerDAO;

    @Autowired
    public PlayerDAOTestController(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPlayer(@RequestBody Player player) {
        if (player.getName() == null || player.getName().isBlank()) {
            return ResponseEntity.badRequest().body("Имя игрока обязательно");
        }
        player.setRegistrationDate(LocalDateTime.now());
        player.setStatus(PlayerStatus.Alive);
        try {
            playerDAO.save(player);
            return ResponseEntity.ok("Игрок сохранён");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка при сохранении игрока");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlayer(@PathVariable Long id, @RequestBody Player updatedPlayer) {
        Optional<Player> optionalPlayer = playerDAO.findById(id);
        if (optionalPlayer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Player player = optionalPlayer.get();

        if (updatedPlayer.getName() == null || updatedPlayer.getName().isBlank()) {
            return ResponseEntity.badRequest().body("Имя игрока обязательно");
        }
        player.setName(updatedPlayer.getName());
        player.setAge(updatedPlayer.getAge());
        player.setBalance(updatedPlayer.getBalance());
        player.setStatus(updatedPlayer.getStatus());
        try {
            playerDAO.update(player);
            return ResponseEntity.ok("Игрок обновлён");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка при обновлении игрока");
        }
    }

    @GetMapping("/{id}/with-results")
    public ResponseEntity<PlayerWithResultsDTO> getPlayerWithResults(@PathVariable Long id) {
        Player player = playerDAO.findByIdWithRoundResults(id);
        if (player == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new PlayerWithResultsDTO(player));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id) {
        return playerDAO.findById(id)
                .map(player -> ResponseEntity.ok(new PlayerDTO(player)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @GetMapping
    public List<PlayerDTO> getAllPlayers() {
        return playerDAO.findAll()
                .stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id) {
        Optional<Player> optionalPlayer = playerDAO.findById(id);
        if (optionalPlayer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            playerDAO.delete(id);
            return ResponseEntity.ok("Игрок удалён");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка при удалении игрока");
        }
    }

    @GetMapping("/status/{status}")
    public List<PlayerDTO> getPlayersByStatus(@PathVariable PlayerStatus status) {
        return playerDAO.findByStatus(status)
                .stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }


    @GetMapping("/native")
    public List<PlayerDTO> getAllPlayersNative() {
        return playerDAO.findAllWithNativeSQL()
                .stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }


    @GetMapping("/balance/{amount}")
    public ResponseEntity<?> getPlayersByBalance(@PathVariable String amount) {
        try {
            BigDecimal bdAmount = new BigDecimal(amount);
            List<Player> players = playerDAO.findByBalanceGreaterThan(bdAmount);
            List<PlayerDTO> playerDTOs = players.stream()
                    .map(PlayerDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(playerDTOs);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Некорректный формат суммы: " + amount);
        }
    }

    @GetMapping("/count/status/{status}")
    public ResponseEntity<Long> countPlayersByStatus(@PathVariable PlayerStatus status) {
        Long count = playerDAO.countByStatus(status);
        return ResponseEntity.ok(count);
    }


}

