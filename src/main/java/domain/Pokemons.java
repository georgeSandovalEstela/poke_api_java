package main.java.domain;


import org.json.JSONObject;

public class Pokemons {
    private String number;
    private String name;
    private String description;

    public Pokemons(JSONObject _pokemon){
        this.name = _pokemon.getString("name");
        String[] _split = _pokemon.getString("url").split("/");
        this.number = _split[_split.length-1];
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
