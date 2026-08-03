import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.textbasedgame.world.roomFactory;
import com.textbasedgame.world.rooms.Room;

public class roomTest {
    @Test
    public void testRoomCreation() {
        roomFactory.setSeed(12345); // Set a seed for reproducibility (Also creates seeded random)
        Room testRoom = roomFactory.getNextRoom();
        assertNotNull(testRoom, "Room should not be null");
    }

    
}
