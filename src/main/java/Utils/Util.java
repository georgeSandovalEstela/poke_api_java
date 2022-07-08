package main.java.Utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.domain.Pokemons;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;

public class Util {
    public static JSONArray getPokemons(int page){
        JSONArray pokemons = null;
        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<JsonNode> response = Unirest.get(Http.POKE_API+String.valueOf(20*page)).asJson();
            JSONObject _object = new JSONObject(response.getBody()).getJSONObject("object");
            pokemons = _object.getJSONArray("results");
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return pokemons;
    }

    public static String getDescriptionPokemon(String numberPokemon){
        String description = "";
        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<JsonNode> response = Unirest.get(Http.POKE_API_POKEMON+numberPokemon+"/").asJson();
            JSONObject _object = new JSONObject(response.getBody()).getJSONObject("object");
            JSONArray text_entries = _object.getJSONArray("flavor_text_entries");
            JSONObject text_entry ;
            for (int i = 0; i < text_entries.length() ; i++) {
                text_entry = text_entries.getJSONObject(i);
                if(text_entry.getJSONObject("language").getString("name").trim().equals("es".trim())){
                    description+=text_entry.get("flavor_text").toString() + " ";
                }
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return description;
    }

    public static Pokemons findPokemonByName(String name){
        Pokemons pokemon = null;
        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<JsonNode> response = Unirest.get(Http.POKE_API_BY_NAME+name).asJson();
            JSONObject _object = new JSONObject(response.getBody()).getJSONObject("object");
            pokemon = new Pokemons();
            pokemon.setNumber(String.valueOf(_object.getInt("id")));
            pokemon.setName(_object.getString("name"));
            pokemon.setDescription(getDescriptionPokemon(pokemon.getNumber()));
        }catch (Exception ex){
            ex.printStackTrace();
            pokemon = null;
        }
        return pokemon;
    }


    public static void showView(String url, Object controller){
        Scene scene = null;
        try{
            Stage stage = new Stage();
            FXMLLoader _loader = new FXMLLoader(Util.class.getClassLoader().getResource(url));
            _loader.setController(controller);
            Parent _pane = _loader.load();
            scene = new Scene(_pane);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Pokemon");
            stage.show();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
