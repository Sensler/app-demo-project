package de.sensler.demobackend.games.controller;

import de.sensler.demobackend.games.model.Game;
import de.sensler.demobackend.games.model.GameNotFoundException;
import de.sensler.demobackend.games.service.GameService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(required = false, defaultValue = "") String name) {
        return ResponseEntity.ok(service.findAll(name));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (GameNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        Game save = service.save(game);
        return ResponseEntity.created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(save.getId())
                        .toUri())
                .body(save);

    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Game game) {
        if (id == null || !id.equals(game.getId()))
            return ResponseEntity.badRequest().body("Resource path and id mismatch");
        service.save(game);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}