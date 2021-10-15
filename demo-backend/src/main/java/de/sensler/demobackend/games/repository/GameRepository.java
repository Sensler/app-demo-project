package de.sensler.demobackend.games.repository;

import de.sensler.demobackend.games.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByName(String name);

    List<Game> findByPublisherContainsIgnoreCase(String publisher);

    List<Game> findAllByName(String name);
    
}
