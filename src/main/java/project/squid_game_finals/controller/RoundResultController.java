//package project.squid_game_finals.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import project.squid_game_finals.entity.RoundResult;
//import project.squid_game_finals.enums.RoundResultStatus;
//import project.squid_game_finals.repository.RoundResultRepository;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/round-results")
//@RequiredArgsConstructor
//public class RoundResultController {
//
//    private final RoundResultRepository roundResultRepository;
//
//    @GetMapping("/by-status")
//    public List<RoundResult> getByStatus(@RequestParam RoundResultStatus status) {
//        return roundResultRepository.findByResult(status);
//    }
//}
