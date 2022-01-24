package components.packages;

import components.cards.cardHandlerImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageHandlerImplTest {

    public PackageHandlerImpl packageHandlerImpl = new PackageHandlerImpl();

    @Test
    void createPackage() {
        assertEquals("201", packageHandlerImpl.createPackage());
    }
}