package components.users;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserImplTest {
    public UserHandlerImpl userHandlerImpl = new UserHandlerImpl();

    @Test
    void createUser() throws SQLException {
        String username = "kiukiu";
        String password = "lol123";

        String return_status = this.userHandlerImpl.register_User(username, password);

        assertEquals(return_status, "400");
    }

    @Test
    void editUser() throws SQLException {
        String username = "kiukiu";
        String name = "Kiu";
        String bio = "lol";
        String image = ">:(";

        String return_status = this.userHandlerImpl.edit_User(username, name, bio, image);

        assertEquals(return_status, "200");
    }
}