package components.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    // [{\"Id\":\"67f9048f-99b8-4ae4-b866-d8008d00c53d\", \"Name\":\"WaterGoblin\", \"Damage\": 10.0}

    Card card = new Card("67f9048f-99b8-4ae4-b866-d8008d00c53d", "10.0", "Water", "Monster", "WaterGoblin");


    @Test
    void getId() {
        assertEquals(card.id, card.getId());
    }

    @Test
    void getDamage() {
        assertEquals(card.damage, card.getDamage());
    }

    @Test
    void getElement() {
        assertEquals(card.element, card.getElement());
    }

    @Test
    void getType() {
        assertEquals(card.type, card.getType());
    }

    @Test
    void getName() {
        assertEquals(card.name, card.getName());
    }

}