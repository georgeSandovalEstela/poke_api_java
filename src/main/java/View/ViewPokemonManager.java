package main.java.View;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.swing.text.html.ImageView;

public class ViewPokemonManager {
    @FXML
    private ImageView pokeImageView;

    @FXML private Label nameLabel;

    @FXML private Label descriptionLabel;

    @FXML private Button closeButton;

    @FXML void closeButton(Event event) {

    }
}
