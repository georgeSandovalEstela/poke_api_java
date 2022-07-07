package Utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

public class Util {
    public static final JSONArray getPokemons(){
        JSONArray pokemons = null;
        try {
            Unirest.setTimeouts(0, 0);
            HttpResponse<JsonNode> response = Unirest.get("https://pokeapi.co/api/v2/pokemon-species?limit=10").asJson();
            System.out.println(response);
            JSONObject _object = new JSONObject(response.getBody()).getJSONObject("object");
            pokemons = _object.getJSONArray("results");
        }catch (Exception ex){
            System.out.println("ERROR");
        }
        return pokemons;
    }
}
