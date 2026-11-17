package com.mrbubbletea.bubblesgunsmithing.item.gunPart.abstracts;


import com.mrbubbletea.bubblesgunsmithing.BubblesGunsmithing;
import com.mrbubbletea.bubblesgunsmithing.item.gunPart.GunPartTypes;
import net.minecraft.world.item.Item;import net.minecraft.world.item.Rarity;


import java.io.IOException;
import java.util.ArrayList;




public abstract class AbstractGunPart extends Item {
    protected GunPartTypes type;
    protected ArrayList<GunPartEntry> entries;

    // Constructor
    public AbstractGunPart(Properties properties) {
        super(properties
                // Default Property values
                .durability(100)
                .stacksTo(1)
                .rarity(Rarity.COMMON)
        );
        GunPartStats stats = GunPartStats.createGunPartStats();
    };


    // Creates an Attachment point for a Gun Part
    protected int createAttachmentPoint(String key, GunPartTypes type, AbstractGunPart value) {
        try {
            GunPartEntry entry = new GunPartEntry(key,type,value);
            entries.add(entry);
            if (!entries.contains(entry)) {throw new IOException("No Entry was inserted into ArrayList");}
        } catch(IOException e) {
            BubblesGunsmithing.LOGGER.error("Error making attachment: ", e);
            return 0; // Failure, Cry and scream to the devs
        }
        return 1; // Successful creation;
    }

    // Abstract Method for attaching a GunPart implementation
    abstract void attach();



    // Helper inner class to store fields of Triples
    protected static class GunPartEntry {
        private final String name;
        private final GunPartTypes required;
        private AbstractGunPart attached;
        protected GunPartEntry(String name, GunPartTypes required, AbstractGunPart attached) {
            this.name = name;
            this.required = required;
            this.attached = attached;
        }

        /*-------------------GETTERS---------------*/
        protected String getName() {
            return name;
        }
        protected GunPartTypes getRequired() {
            return required;
        }
        protected AbstractGunPart getAttached() {
            return attached;
        }
        /*-------------------SETTERS---------------*/
        // Returns the previous part if applicable
        protected AbstractGunPart setGunPart(AbstractGunPart newPart) {
            AbstractGunPart toReturn = attached;
            attached = newPart;
            return toReturn;
        }
        // Returns the previous part and leaves the slot empty
        protected  AbstractGunPart setGunPart() {
            AbstractGunPart toReturn = attached;
            attached = null;
            return toReturn;
        }
    }

    // Protected class that carries the attachments stats
    protected static class GunPartStats {
        // Damage
        double damageMelee = 0.0; // Melee Damage
        float damageMultiplier = 0f; // Ranged Damage Multiplier
        // Durability Modifiers
        double heatTolerance = 0.0; // How much tolerance a gun part can protect itself from sustained fire
        double stressTolerance = 0.0; // How much tolerance a gun part can protect itself from cartridge operations and such
        double barrelStrength = 0.0; // How strong the barrel can handle for a round; if too low, gun MAY explode.
        // Accuracy
        float spread = 0f; // How accurate is the projectiles from the center of aim
        float recoilVertical = 0f; // Vertical Recoil
        float recoilHorizontal = 0f; // Horizontal Recoil
        // Speed
        float projectileSpeed = 0f; // How fast the projectile leaves the gun
        double reloadSpeed = 0.0; // How fast can the player can reload the gun
        double adsSpeed = 0.0; // How fast the player can aim down sights
        // Capacity
        int ammoCapacity = 0; // How much ammo can the part hold if applicable; defaults to zero
        double weight = 0.0; // How heavy the gun weighs, higher values = slower player movement speed
        // Length
        double totalLength = 0.0; // Total length of the GunPart, gun MAY bump into things
        // Booleans
        boolean needsWorkbench = true; // Does the GunPart require a workbench to attach it

        private GunPartStats() {
        }

        // Factory Method to create GunPartStats instance
        protected static GunPartStats createGunPartStats() {
            return new GunPartStats();
        }
    }
}

