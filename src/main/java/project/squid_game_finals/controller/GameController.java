//package project.squid_game_finals.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import project.squid_game_finals.entity.Games;
//import project.squid_game_finals.service.GameService;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/games")
//public class GameController {
//
//    @Autowired
//    private GameService gameService;
//
//    // Получить все игры
//    @GetMapping
//    public List<Games> getAllGames() {
//        return gameService.getAllGames();
//    }
//
//    // Получить игру по ID
//    @GetMapping("/{id}")
//    public Optional<Games> getGameById(@PathVariable Long id) {
//        return gameService.getGameById(id);
//    }
//
//    // Создать новую игру
//    @PostMapping
//    public Games createGame(@RequestBody Games game) {
//        return gameService.createGame(game);
//    }
//
//    // Обновить информацию об игре
//    @PutMapping("/{id}")
//    public Games updateGame(@PathVariable Long id, @RequestBody Games game) {
//        game.setId(id);
//        return gameService.updateGame(game);
//    }
//
//    // Удалить игру
//    @DeleteMapping("/{id}")
//    public void deleteGame(@PathVariable Long id) {
//        gameService.deleteGame(id);
//    }
//}
