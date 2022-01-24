package components.utils;

public class Card {

    String id;
    String damage;
    String element;
    String type;
    String name;

    public Card(String id, String damage, String element, String type, String name) {
        this.id = id;
        this.damage = damage;
        this.element = element;
        this.type = type;
        this.name = name;
    }

    public String getId(){
        return this.id;
    }

    public String getDamage(){
        return this.damage;
    }

    public String getElement(){
        return this.element;
    }

    public String getType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public void setDamage(String damage){
        this.damage = damage;
    }

}
