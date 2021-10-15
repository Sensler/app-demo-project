package de.sensler.demobackend.games.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    /**
     * Name des Spiels
     */
    private String name;

    /**
     * Name des Publishers
     */
    private String publisher;

    /**
     * Kaufjahr
     */
    private int buyingYear;
}
