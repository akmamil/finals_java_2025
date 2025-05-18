//package project.squid_game_finals.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import project.squid_game_finals.entity.Player;
//import project.squid_game_finals.repository.PlayerRepository;
//import project.squid_game_finals.enums.PlayerStatus;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class PlayerService {
//
//    @Autowired
//    private PlayerRepository playerRepository;
//
//    // Метод для получения всех игроков
//    public List<Player> getAllPlayers() {
//        return playerRepository.findAll();
//    }
//
//    // Метод для получения игрока по статусу
//    public List<Player> getPlayersByStatus(PlayerStatus status) {
//        return playerRepository.findByStatus(status);
//    }
//
//    // Метод для получения игрока по ID
//    public Optional<Player> getPlayerById(Long id) {
//        return playerRepository.findById(id);
//    }
//
//    // Метод для создания нового игрока
//    public Player createPlayer(Player player) {
//        return playerRepository.save(player);
//    }
//
//    // Метод для обновления игрока
//    public Player updatePlayer(Player player) {
//        return playerRepository.save(player);
//    }
//
//    // Метод для удаления игрока
//    public void deletePlayer(Long id) {
//        playerRepository.deleteById(id);
//    }
//}

