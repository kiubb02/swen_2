package components.deck;

public interface DeckHandlerInterface {
    public String getCardsofDeck(String username);
    public String getCardsofDeckPlain(String username);
    public String updateDeck(String username, String card_id);
    public boolean checkOwner(String username, String card_id);
}
