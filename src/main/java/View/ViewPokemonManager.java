package main.java.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.java.Utils.Http;
import main.java.Utils.Util;
import main.java.domain.Pokemons;

public class ViewPokemonManager {
    private Pokemons pokemon;
    private ListController listController;
    @FXML
    private ImageView pokeImageView;

    @FXML
    private Label nameLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Button closeButton;

    public ViewPokemonManager(Pokemons pokemon,ListController listController) {
        this.pokemon = pokemon;
        this.listController = listController;
    }

    @FXML
    void closeButton(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        Util.showView("View/listPokemon.fxml", listController);
    }
    private void loadPokemonData(){
        this.pokeImageView.setImage(new Image(Http.POKE_API_IMG+this.pokemon.getNumber()+".png"));
        this.nameLabel.setText("----------"+this.pokemon.getName()+"----------");
        this.descriptionLabel.setText(pokemon.getDescription());
    }
    @FXML
    private void initialize(){
        loadPokemonData();
    }
}
