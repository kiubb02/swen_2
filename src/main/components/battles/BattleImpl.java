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
        // else just wait for the other player to join
        return new Response(HttpStatus.NOT_FOUND, ContentType.JSON, HttpStatus.NOT_FOUND.message + " \" No Battles Found.\"");

    }

    @Override
    public void battle(String user, String opponent) {
        Logger.getInstance();
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

            //every 5 wins => boost
            if(userwon%5 == 0) cardA.setSpecial(true);
            if(oppwon%5 == 0) cardB.setSpecial(true);
            //every 10 loss => boost for other player
            if(userlost%10 == 0) cardB.setSpecial(true);
            if(opplost%10 == 0) cardA.setSpecial(true);

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
                    eloUser = this.calculateELO(eloUser, eloOpp, 0.5);
                    eloOpp = this.calculateELO(eloOpp, eloUser, 0.5);
                }
                case "Skip" -> {
                    Logger.log("This round will be skipped .. \n And no Elo will be changed \n");
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

        //Name1
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

        //Name2
        if (name2.contains("Goblin") && name1.contains("Dragon")) {
            second.setDamage("0");
            Logger.log("Goblin too afraid to attack\n");
        } else if (name2.contains("Knight") && name1.contains("WaterSpell")) {
            second.setDamage("0");
            Logger.log("Knight drowned\n");
        } else if (name2.contains("FireElve") && name1.contains("Dragon")) {
            first.setDamage("0");
            Logger.log("FireElve evaded the attack\n");
        } else if (name2.contains("Kraken") && name1.contains("Spell")) {
            first.setDamage("0");
            Logger.log("Kraken is immune\n");
        } else if (name2.contains("Wizzard") && name1.contains("Ork")) {
            first.setDamage("0");
            Logger.log("Ork isnt able to attack\n");
        }
    }

    @Override
    public String calculateDMG(Card first, Card second) {
        double dmgCardA = Double.parseDouble(first.getDamage());
        double dmgCardB = Double.parseDouble(second.getDamage());
        int a = 2;
        int b = 2;

        //first check if a round will be skipped or not
        if(first.getType().equals("Skip")) return "Skip";
        if(second.getType().equals("Skip")) return "Skip";

        //check if a special is set
        if(first.getSpecial().equals(true)) a = 4;
        if(second.getSpecial().equals(true)) b = 4;

        if(first.getType().equals("Spell") || second.getType().equals("Spell")){
            //for cardA
            if(first.getElement().equals("Water") && second.getElement().equals("Fire")) {
                dmgCardB = dmgCardB/2;
                dmgCardA = dmgCardA*a;
            }
            if(first.getElement().equals("Fire") && second.getElement().equals("Regular")) {
                dmgCardB = dmgCardB/2;
                dmgCardA = dmgCardA*a;
            }
            if(first.getElement().equals("Regular") && second.getElement().equals("Water")) {
                dmgCardB = dmgCardB/2;
                dmgCardA = dmgCardA*a;
            }
            if(first.getElement().equals("Earth") && second.getElement().equals("Fire")) {
                dmgCardB = dmgCardB/2;
                dmgCardA = dmgCardA*a;
            }
            if(first.getElement().equals("Ice") && second.getElement().equals("Water")) {
                dmgCardB = dmgCardB/2;
                dmgCardA = dmgCardA*a;
            }

            //for cardB
            if(second.getElement().equals("Water") && first.getElement().equals("Fire")) {
                dmgCardA = dmgCardA/2;
                dmgCardB = dmgCardB*b;
            }
            if(second.getElement().equals("Fire") && first.getElement().equals("Regular")) {
                dmgCardA = dmgCardA/2;
                dmgCardB = dmgCardB*b;
            }
            if(second.getElement().equals("Regular") && first.getElement().equals("Water")) {
                dmgCardA = dmgCardA/2;
                dmgCardB = dmgCardB*b;
            }
            if(second.getElement().equals("Earth") && first.getElement().equals("Fire")) {
                dmgCardA = dmgCardA/2;
                dmgCardB = dmgCardB*b;
            }
            if(second.getElement().equals("Ice") && first.getElement().equals("Water")) {
                dmgCardA = dmgCardA/2;
                dmgCardB = dmgCardB*b;
            }

        }

        //set speciality back
        first.setSpecial(false);
        second.setSpecial(false);

        if(dmgCardA > dmgCardB) return "Won";
        if(dmgCardA < dmgCardB) return "Lost";
        if(dmgCardA == dmgCardB) return "Draw";

        return null;
    }

    @Override
    public int calculateELO(int eloUser, int eloOpp, double a) {
        int k = 40;
        int r;
        if (eloUser>=2400&&eloOpp>=2400) {
            k = 10;
        }
        r = 1/(1+k^(eloOpp-eloUser)/400);
        return (int) (eloUser+k*(a-r));
    }
}
