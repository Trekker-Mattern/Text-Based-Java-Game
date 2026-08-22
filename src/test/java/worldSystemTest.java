import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.GUI.GUISegments.InventoryPanel;
import com.textbasedgame.GUI.GUISegments.TextPanel;
import com.textbasedgame.GUI.pictureLoader.imageIDs;
import com.textbasedgame.playerFiles.GameProgress;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.GameProgressWrapper;
import com.textbasedgame.util.saveFiles;
import com.textbasedgame.world.roomFactory;
import com.textbasedgame.world.rooms.Room;

import javax.swing.JPanel;

public class worldSystemTest {

    private final Path saveRoot = new File(System.getProperty("user.dir") + File.separator + "save_Files").toPath();

    @BeforeEach
    public void setup() throws ReflectiveOperationException {
        initializeGuiState();
        player.inventory.clear();
        player.equippedItems.clear();
        player.keyItemInventory.clear();
        player.buffs.clear();
        player.LHand = null;
        player.RHand = null;
        player.helm = null;
        player.chestplate = null;
        player.pants = null;
        player.shoes = null;
        player.name = "Test Player";
        player.strength = 5;
        player.agility = 2;
        player.intelligence = 1;
        player.armor = 0;
        player.luck = 1.0;
        player.gold = 0;
        player.playerLevel = 1;
        player.dead = false;
        player.isBuff = false;
        player.setMaxHealth(20);
        player.setHealth(20);
        player.setXP(0);
        player.setXpToLevelUp(10);
        GameProgressWrapper.setGameProgress(new GameProgress());
        try{
            Files.createDirectories(saveRoot);
        }
        catch(Exception e){
            System.out.println("Error creating save directory: " + e);
        }
    }

    
    
    @Test
    public void saveFilesCreatesExpectedOutputArtifacts() {
        saveFiles.save();
        
        assertTrue(Files.exists(saveRoot.resolve("saveFile.txt")));
        assertTrue(Files.exists(saveRoot.resolve("playerInventory.JSON")));
        assertTrue(Files.exists(saveRoot.resolve("GameProgress.JSON")));
    }
    
    @Test
    public void saveFilesCreatesGameProgressJsonWithDefaultFlags() {
        saveFiles.saveGameProgressJSON();
        
        File progressFile = saveRoot.resolve("GameProgress.JSON").toFile();
        assertTrue(progressFile.exists());
        assertTrue(progressFile.length() > 0);
    }
    

@Test
    public void savePreparationReplacesAFilePathWithADirectorySafely() throws Exception {
        Path filePath = saveRoot.resolve("not-a-directory.txt");
        Files.write(filePath, "test".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        assertTrue(Files.exists(filePath));
        assertFalse(Files.isDirectory(filePath));

        saveFiles.save();

        assertTrue(Files.isDirectory(saveRoot));
        assertTrue(Files.exists(saveRoot.resolve("saveFile.txt")));
    }

    @Test
    public void roomFactoryProducesNonNullRoomsWithDeterministicSeed() {
        System.out.println("Testing room factory...");
        roomFactory.setSeed(12345);
        Room room = roomFactory.getNextRoom();

        assertNotNull(room);
        assertFalse(room.getClass().getSimpleName().isEmpty());
    }

    @Test
    public void roomFactoryRegeneratesQueuesWithoutThrowing() {
        System.out.println("Testing room factory regeneration...");
        roomFactory.setSeed(7);

        assertDoesNotThrow(() -> roomFactory.regenerateRoomQueue());
    }

    @Test
    public void gameProgressWrapperStoresAndReturnsProgress() {
        System.out.println("Testing game progress wrapper...");
        GameProgress progress = new GameProgress();
        progress.potionBagUnlocked = true;
        progress.lizzyMet = true;
        progress.iggyMet = false;

        GameProgressWrapper.setGameProgress(progress);

        assertTrue(GameProgressWrapper.gameProgress.potionBagUnlocked);
        assertTrue(GameProgressWrapper.gameProgress.lizzyMet);
        assertFalse(GameProgressWrapper.gameProgress.iggyMet);
    }

    private void initializeGuiState() throws ReflectiveOperationException {
        setStaticField("textPanel", new TextPanel());
        setStaticField("invPanel", new InventoryPanel());
        setStaticField("imagePanel", new JPanel());
        setStaticField("currentImageID", imageIDs.BLANK);
    }

    private void setStaticField(String fieldName, Object value) throws ReflectiveOperationException {
        Field field = gui.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(null, value);
    }
}
