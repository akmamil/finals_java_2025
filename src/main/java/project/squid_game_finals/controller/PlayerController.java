//package project.squid_game_finals.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import project.squid_game_finals.entity.Player;
//import project.squid_game_finals.service.PlayerService;
//import project.squid_game_finals.enums.PlayerStatus;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/players")
//public class PlayerController {
//
//    @Autowired
//    private PlayerService playerService;
//
//    // Получить всех игроков
//    @GetMapping
//    public List<Player> getAllPlayers() {
//        return playerService.getAllPlayers();
//    }
//
//    // Получить игроков по статусу
//    @GetMapping("/status/{status}")
//    public List<Player> getPlayersByStatus(@PathVariable PlayerStatus status) {
//        return playerService.getPlayersByStatus(status);
//    }
//
//    // Получить игрока по ID
//    @GetMapping("/{id}")
//    public Optional<Player> getPlayerById(@PathVariable Long id) {
//        return playerService.getPlayerById(id);
//    }
//
//    // Создать нового игрока
//    @PostMapping
//    public Player createPlayer(@RequestBody Player player) {
//        return playerService.createPlayer(player);
//    }
//
//    // Обновить информацию об игроке
//    @PutMapping("/{id}")
//    public Player updatePlayer(@PathVariable Long id, @RequestBody Player player) {
//        player.setId(id);
//        return playerService.updatePlayer(player);
//    }
//
//    // Удалить игрока
//    @DeleteMapping("/{id}")
//    public void deletePlayer(@PathVariable Long id) {
//        playerService.deletePlayer(id);
//    }
//}

