package components.battles;

import components.utils.Card;
import components.utils.CardType;
import server.Response;
import server.http.ContentType;
import server.http.HttpStatus;
import server.request.Request;

import java.sql.SQLException;
import java.util.ArrayList;

public class BattleImpl implements Battle{
    private final BattleHandlerImpl BattleH = new BattleHandlerImpl();

    @Override
    public Response start(Request request) throws SQLException {

        String message = this.BattleH.checkBattles(request.getUsername());
        if(message.contains("200")) {
            String[] op = message.split(" ");
            this.battle(request.getUsername(), op[1]);
            return new Response(HttpStatus.OK, ContentType.JSON, HttpStatus.OK.message + " \" Battle found and fought.\"");

        }
        return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, HttpStatus.NOT_FOUND.message + " \" No Battles Found.\"");

    }

    @Override
    public void battle(String user, String opponent) {
        //Stats
        int userwon = 0;
        int userlost = 0;
        int oppwon = 0;
        int opplost = 0;
        //Decks
        ArrayList<Card> oppCards = BattleH.getDeck(opponent);
        ArrayList<Card> userCards = BattleH.getDeck(user);
        //ELO
        int eloUser = BattleH.getElo(user);
        int eloOpp = BattleH.getElo(opponent);

        //Start rounds
        int rounds;
        for (rounds = 100; rounds > 0; rounds--) {
            Logger.log("Round: " + rounds + "\n");
            //get cards for this round
            Card cardA = userCards.get((int) (Math.random() * userCards.size()));
            Card cardB = oppCards.get((int) (Math.random() * oppCards.size()));
            Logger.log(user + " picked : " + cardA.getName() + "\n");
            Logger.log(opponent + " picked : " + cardB.getName() + "\n");
            checkSpeciality(cardA, cardB);
            checkSpeciality(cardB, cardA);

            switch(calculateDMG(cardA, cardB)){
                //user wins
                case "Won" -> {
                    userCards.add(cardB);
                    oppCards.remove(cardB);
                    eloUser = this.calculateELO(eloUser, eloOpp, 1);
                    eloOpp = this.calculateELO(eloOpp, eloUser, 0);
                    userwon++;
                    opplost++;
                    Logger.log(user + " won this round\n");
                    Logger.log(opponent + " lost this round\n");
                }
                //opponent wins
                case "Lost" -> {
                    oppCards.add(cardA);
                    userCards.remove(cardA);
                    eloUser = this.calculateELO(eloUser, eloOpp, 0);
                    eloOpp = this.calculateELO(eloOpp, eloUser, 1);
                    oppwon++;
                    userlost++;
                    Logger.log(opponent + " won this round\n");
                    Logger.log(user + " lost this round\n");
                }
                case "Draw" -> {
                    Logger.log("draw\n");
                    //change 2 => 0.5
                    eloUser = this.calculateELO(eloUser, eloOpp, 0.5);
                    eloOpp = this.calculateELO(eloOpp, eloUser, 0.5);
                }
            }
            if (oppCards.size() ==0 || userCards.size() == 0) {
                break;
            }
        }
        Logger.log("Battle finished .. \n");
        BattleH.updateBattleStats(user, userlost, userwon, eloUser);
        BattleH.updateBattleStats(opponent, opplost, oppwon, eloOpp);
        BattleH.updateState(user, "...");
        BattleH.updateState(opponent, "...");

    }

    @Override
    public void checkSpeciality(Card first, Card second) {
        String name1 = first.getName();
        String name2 = second.getName();

        if (name1.contains("Goblin") && name2.contains("Dragon")) {
            first.setDamage("0");
            Logger.log("Goblin too afraid to attack\n");
        } else if (name1.contains("Knight") && name2.contains("WaterSpell")) {
            first.setDamage("0");
            Logger.log("Knight drowned\n");
        } else if (name1.contains("FireElve") && name2.contains("Dragon")) {
            second.setDamage("0");
            Logger.log("FireElve evaded the attack\n");
        } else if (name1.contains("Kraken") && name2.contains("Spell")) {
            second.setDamage("0");
            Logger.log("Kraken is immune\n");
        } else if (name1.contains("Wizzard") && name2.contains("Ork")) {
            second.setDamage("0");
            Logger.log("Ork isnt able to attack\n");
        }
    }

    @Override
    public String calculateDMG(Card first, Card second) {
        double dmgCardA = Double.parseDouble(first.getDamage());
        double dmgCardB = Double.parseDouble(second.getDamage());

        if(first.getType().equals("Spell") || second.getType().equals("Spell")){

        }

        if(dmgCardA > dmgCardB) return "Won";
        if(dmgCardA < dmgCardB) return "Lost";
        if(dmgCardA == dmgCardB) return "Draw";

        return null;
    }

    @Override
    public int calculateELO(int eloUser, int eloOpp, double a) {
        return 0;
    }


}
