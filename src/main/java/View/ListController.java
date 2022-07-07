package main.java.View;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import main.java.Utils.Http;
import main.java.Utils.Util;
import main.java.domain.Pokemons;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ListController {

    private JSONArray pokemons;
    private List<Pokemons> pokemonsList;
    private int page;

    public ListController() {
        this.page = 1;
    }
    private Label nameLabel;
    private ImageView pokeImage;
    private static final int width = 120;
    private static final int height = 120;
    @FXML private GridPane gridPane;
    @FXML private Button previousButton,nextButton;


    private void insertPokemonToGridPane(){
        int countPokemon = 0;
        for (int i = 0; i < gridPane.getColumnConstraints().size(); i++){
            for (int j = 0; j < gridPane.getRowConstraints().size(); j++){
                AnchorPane _anchor = new AnchorPane();
                _anchor.setPrefSize(width,height);
                _anchor.getChildren().addAll(new ImageView(new Image(Http.POKE_API_IMG+this.pokemonsList.get(countPokemon).getNumber()+".png")), new Label("  "+this.pokemonsList.get(countPokemon).getName()));
                gridPane.add(_anchor,j,i);
                countPokemon++;
            }
        }
    }

    private void clearGridPane(){
        gridPane.getChildren().clear();
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();
    }

    private void initGridPane(){
        clearGridPane();
        for (int i = 0; i < 5; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(width));
            if(i!=4) gridPane.getRowConstraints().add(new RowConstraints(height));
        }
    }

    private void getPokemons(){
        this.pokemons = Util.getPokemons(page);
        jsonToPokemon();
    }

    private void jsonToPokemon(){
        this.pokemonsList = new ArrayList<>();
        for (int i = 0; i < this.pokemons.length() ; i++)
            this.pokemonsList.add(new Pokemons(this.pokemons.getJSONObject(i)));
    }

    private void showPokemons(){
        getPokemons();
        initGridPane();
        insertPokemonToGridPane();
    }

    private void clickPokemon(Event event){

    }

    @FXML
    private void nextButton(ActionEvent event) {
        page++;
        showPokemons();
    }

    @FXML
    private void previousButton(ActionEvent event) {
        int _oldPage = page;
        if (page>1) page--;
        if(_oldPage!=page) showPokemons();
    }

    @FXML private void initialize(){
        getPokemons();
        initGridPane();
        insertPokemonToGridPane();
    }
}
