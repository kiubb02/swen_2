package components.stats;

import db.databaseInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatsHandlerImpl implements StatsHandler{
    @Override
    public String getStats(String username) throws SQLException {

        String message = "200";

        int elo = 0;
        int won = 0;
        int lost = 0;
        float ratio = 0;

        try {
            Connection con = databaseInterface.getConnection(); //connect to the database
            assert con != null;
            //create prepared statement
            PreparedStatement stmt = con.prepareStatement("""
                    SELECT elo,won,lost
                    FROM users
                    WHERE "username" = ?
                    """);
            stmt.setString(1, username);

            ResultSet res = stmt.executeQuery();

            //check if empty
            //return 404
            if(!res.isBeforeFirst()){
                return "404";
            }

            //else go through all the results and save
            while(res.next()){
                elo = res.getInt("elo");
                lost = res.getInt("lost");
                won = res.getInt("won");
                ratio = won/lost;
            }

            message += "\n{\"Name\":\"" + username + "\",\"Elo\":\"" + elo + "\",\"Won\":\"" + won + "\",\"Lost\":\"" + lost + "\",\\\"Win/Loss\\\":\\\"\"" + ratio + "\"\\\"}";

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
            message = "500";
        }

        return message;
    }
}
