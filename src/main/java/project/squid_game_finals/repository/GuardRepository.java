package project.squid_game_finals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.squid_game_finals.entity.Guard;

@Repository
public interface GuardRepository extends JpaRepository<Guard, Long> {
}

