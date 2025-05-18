//package project.squid_game_finals.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import project.squid_game_finals.entity.Games;
//import project.squid_game_finals.repository.GameRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class GameService {
//
//    @Autowired
//    private GameRepository gameRepository;
//
//    // Метод для получения всех игр
//    public List<Games> getAllGames() {
//        return gameRepository.findAll();
//    }
//
//    // Метод для получения игры по ID
//    public Optional<Games> getGameById(Long id) {
//        return gameRepository.findById(id);
//    }
//
//    // Метод для создания новой игры
//    public Games createGame(Games game) {
//        return gameRepository.save(game);
//    }
//
//    // Метод для обновления игры
//    public Games updateGame(Games game) {
//        return gameRepository.save(game);
//    }
//
//    // Метод для удаления игры
//    public void deleteGame(Long id) {
//        gameRepository.deleteById(id);
//    }
//}

