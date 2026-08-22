import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.GUI.GUISegments.InventoryPanel;
import com.textbasedgame.GUI.GUISegments.TextPanel;
import com.textbasedgame.GUI.pictureLoader.imageIDs;
import com.textbasedgame.items.item;
import com.textbasedgame.items.consumableItems.bread;
import com.textbasedgame.items.handItems.club;
import com.textbasedgame.items.handItems.dagger;
import com.textbasedgame.playerFiles.player;
import com.textbasedgame.util.saveFiles;

import javax.swing.JPanel;

public class playerSystemTest {

    @BeforeEach
    public void resetPlayerState() {
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
        player.strength = 0;
        player.agility = 0;
        player.intelligence = 0;
        player.armor = 0;
        player.luck = 1.0;
        player.gold = 0;
        player.playerLevel = 1;
        player.dead = false;
        player.isBuff = false;
        player.setMaxHealth(0);
        player.setHealth(0);
        player.setXP(0);
        player.setXpToLevelUp(10);
    }

    @Test
    public void allocateSkillPointsSetsDerivedStats() {
        player.allocateSkillPoints(5, 2, 1);

        assertEquals(5, player.strength);
        assertEquals(2, player.agility);
        assertEquals(1, player.intelligence);
        assertEquals(22, player.getMaxHealth());
        assertEquals(22, player.getHealth());
    }

    @Test
    public void majorityStatDetectionMatchesHighestAttribute() {
        System.out.println("Testing majority stat detection...");
        player.allocateSkillPoints(4, 2, 1);

        assertTrue(player.isMajorityStat(player.buffTypes.STRENGTH));
        assertFalse(player.isMajorityStat(player.buffTypes.AGILITY));
        assertFalse(player.isMajorityStat(player.buffTypes.INTELLIGENCE));
    }

    @Test
    public void addHealthCapsAtMaximumHealth() {
        System.out.println("Testing health cap...");
        player.allocateSkillPoints(3, 1, 0);
        player.setHealth(5);

        player.addHealth(100);

        assertEquals(player.getMaxHealth(), player.getHealth());
    }

    @Test
    public void addingConsumablesStacksItemsInsteadOfDuplicating() {
        System.out.println("Testing consumable stacking...");
        bread firstBread = new bread();
        bread secondBread = new bread();

        player.addItemToPlayer(firstBread);
        player.addItemToPlayer(secondBread);

        assertEquals(1, player.inventory.size());
        assertTrue(player.inventory.get(0) instanceof bread);
        assertEquals(2, ((bread) player.inventory.get(0)).getStackValue());
    }

    @Test
    public void getItemToAddToInvCreatesExpectedInstance() {
        System.out.println("Testing item creation...");
        item createdItem = saveFiles.getItemToAddToInv(bread.class);

        assertNotNull(createdItem);
        assertTrue(createdItem instanceof bread);
    }

    @Test
    public void equippingAHandItemAssignsItToAnAvailableSlot() {
        System.out.println("Testing equipping a hand item...");
        club clubItem = new club();

        player.addItemToPlayer(clubItem, true);

        assertTrue(player.equippedItems.contains(clubItem));
        assertEquals(clubItem, player.RHand);
    }

    @Test
    public void equippingSecondHandItemUsesTheOtherHandWhenFirstIsOccupied() {
        System.out.println("Testing equipped hand swapping...");
        club firstClub = new club();
        dagger secondDagger = new dagger();

        player.addItemToPlayer(firstClub, true);
        player.addItemToPlayer(secondDagger, true);

        assertEquals(firstClub, player.RHand);
        assertEquals(secondDagger, player.LHand);
        assertTrue(player.equippedItems.contains(firstClub));
        assertTrue(player.equippedItems.contains(secondDagger));
    }

    @Test
    public void removingEquippedItemCleansUpItsSlotAndInventoryEntry() {
        System.out.println("Testing removal of equipped item...");
        club clubItem = new club();
        player.addItemToPlayer(clubItem, true);

        player.removeItemFromPlayer(clubItem);

        assertFalse(player.inventory.contains(clubItem));
        assertNull(player.RHand);
        assertFalse(player.equippedItems.contains(clubItem));
    }

    private void initializeGuiState() {
        try {
            setStaticField("textPanel", new TextPanel());
            setStaticField("invPanel", new InventoryPanel());
            //setStaticField("topofInvPanel", new JPanel());
            setStaticField("imagePanel", new JPanel());
            setStaticField("currentImageID", imageIDs.BLANK);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to initialize GUI test state", e);
        }
    }

    private void setStaticField(String fieldName, Object value) throws ReflectiveOperationException {
        Field field = gui.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(null, value);
    }
}
