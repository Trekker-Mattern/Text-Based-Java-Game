import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.textbasedgame.world.roomFactory;
import com.textbasedgame.world.rooms.Room;

public class roomTest {
    @Test
    public void testRoomCreation() {
        Room testRoom = roomFactory.getRandomRoom();
        assertNotNull(testRoom, "Room should not be null");
    }

    
}
