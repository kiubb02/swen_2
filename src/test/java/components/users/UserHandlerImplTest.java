package components.users;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserHandlerImplTest {
    public UserHandlerImpl userHandlerImpl = new UserHandlerImpl();

    @Test
    void register_User() throws SQLException {
        String username = "kiukiu";
        String password = "lol123";
        assertEquals("201", userHandlerImpl.register_User(username, password));
    }

    @Test
    void show_User() throws SQLException {
        String username = "kiu";
        assertEquals("404", userHandlerImpl.show_User(username));
    }

    @Test
    void edit_User() throws SQLException {
        String username = "kiukiu";
        String name = "Kiu";
        String bio = "lol";
        String image = ">:(";
        assertEquals("200", userHandlerImpl.edit_User(username, name, bio, image));
    }
}