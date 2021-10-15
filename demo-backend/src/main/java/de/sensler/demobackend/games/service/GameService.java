package de.sensler.demobackend.games.service;

import de.sensler.demobackend.games.model.Game;
import de.sensler.demobackend.games.model.GameNotFoundException;
import de.sensler.demobackend.games.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class GameService {

    private final Logger logger = LoggerFactory.getLogger(GameService.class);

    private final GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        repository.save(createGame("Minecraft", "Mojang", 2009));
        repository.save(createGame("Diablo 3", "Blizzard Entertainment", 2012));
        repository.save(createGame("Starcraft 2", "Blizzard Entertainment", 2010));
        repository.save(createGame("My big pony", null, 2020));

        logger.info("********** findAll **********");
        repository.findAll().forEach(game -> logger.info(game.toString()));

        logger.info("********** findById(1l) **********");
        logger.info(repository.findById(1L).get().toString());

        logger.info("********** findByName() **********");
        repository.findByName("Diablo 3").forEach(game -> logger.info(game.toString()));

        logger.info("********** findByPublisherContainsIgnoreCase() **********");
        repository.findByPublisherContainsIgnoreCase("BLIZZ").forEach(game -> logger.info(game.toString()));

        repository.findByPublisherContainsIgnoreCase("A").forEach(game -> logger.info(game.toString()));

    }

    /*Hilfsmethode für das Erzeugen eines Spiels */
    private Game createGame(String name, String publisher, int buyingYear) {
        Game game = new Game();
        game.setName(name);
        game.setPublisher(publisher == null ? "Ich" : publisher);
        game.setBuyingYear(buyingYear);
        return game;
    }

    public List<Game> findAll(String name) {
        return name.isBlank() ? repository.findAll() : repository.findAllByName(name);
    }

    public Game findById(Long id) throws GameNotFoundException {
        return repository.findById(id).orElseThrow(() -> new GameNotFoundException("Game with id= " + id + " not found."));
    }

    public Game save(Game entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
