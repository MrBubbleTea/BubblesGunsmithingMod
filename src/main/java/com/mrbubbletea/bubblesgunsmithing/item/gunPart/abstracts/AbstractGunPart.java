package com.mrbubbletea.bubblesgunsmithing.item.gunPart.abstracts;


import com.mrbubbletea.bubblesgunsmithing.BubblesGunsmithing;
import com.mrbubbletea.bubblesgunsmithing.item.gunPart.GunPartTypes;
import net.minecraft.world.item.Item;import net.minecraft.world.item.Rarity;


import java.io.IOException;
import java.util.ArrayList;




public abstract class AbstractGunPart extends Item {
    protected GunPartTypes type;
    protected ArrayList<GunPartEntry> entries = new ArrayList<>();
    protected GunPartStats stats;
    // Constructor
    public AbstractGunPart(Properties properties) {
        super(properties
                // Default Property values
                .durability(100)
                .rarity(Rarity.COMMON)
        );
        this.stats = GunPartStats.createGunPartStats();
    }


    // Creates an Attachment point for a Gun Part
    protected int createAttachmentPoint(String key, ArrayList<GunPartTypes> type, AbstractGunPart value, float x, float y, float z) {
        try {
            GunPartEntry entry = new GunPartEntry(key,type,value,x,y,z);
            boolean check = entries.add(entry);
            if (check) {throw new IOException("No Entry was inserted into ArrayList");}
        } catch(IOException e) {
            BubblesGunsmithing.LOGGER.error("Error making attachment: ", e);
            return 0; // Failure, Cry and scream to the devs
        }
        return 1; // Successful creation;
    }
    // value
    protected int createAttachmentPoint(String key, ArrayList<GunPartTypes> type, float x, float y, float z) {
        return createAttachmentPoint(key,type,null,x,y,z);
    }

    // Abstract Method for attaching a GunPart implementation
    abstract void attach(AbstractGunPart newGunPart);



    // Helper inner class to store fields for gun part connection points
    protected static class GunPartEntry {
        // Fields
        private final String name;
        private final ArrayList<GunPartTypes> required;
        private AbstractGunPart attached;
        private final float x,y,z;
        // Constructors
        // For god knows what you want to use this constructor for
        protected GunPartEntry(String name, ArrayList<GunPartTypes> required, AbstractGunPart attached) {
            this(name,required,attached,0f,0f,0f);
        }
        // The values of the GunPart's Attachement point; The name of the point, the required part
        protected GunPartEntry(String name, ArrayList<GunPartTypes> required, AbstractGunPart attached, float x, float y, float z) {
            this.name = name;
            this.required = new ArrayList<>(required);
            this.attached = attached;
            // Location on the gun the attachment point is located in
            this.x = x;
            this.y = y;
            this.z = z;
        }

        /*-------------------GETTERS---------------*/
        protected String getName() {
            return name;
        }
        protected ArrayList<GunPartTypes> getRequired() {
            return required;
        }
        protected AbstractGunPart getAttached() {
            return attached;
        }
        protected float getX() { return x;}
        protected float getY() { return y;}
        protected float getZ() { return z;}
        protected float[] getXYZ() { return new float[]{getX(),getY(),getZ()}; }
        /*-------------------SETTERS---------------*/
        // Returns the previous part if applicable
        protected AbstractGunPart setGunPart(AbstractGunPart newPart) {
            if (!checkValidGunPart(newPart)) {
                // call some sort of notif method, maybe a sound event or something
                return null; // Prevents any interaction
            }
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
        protected boolean checkValidGunPart(AbstractGunPart newPart) {
            if (newPart == null) {return false;}
            return required.contains(newPart.type);
        }
    }

    // Inner Protected class that carries the attachments stats
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
            try {
                BubblesGunsmithing.LOGGER.info("GunPart Stats created");
            } catch (RuntimeException e) {
                BubblesGunsmithing.LOGGER.error("Unable to create GunPart's Stats: ",e);
            }
        }

        // Factory Method to create GunPartStats instance
        protected static GunPartStats createGunPartStats() {
            return new GunPartStats();
        }
    }
}

