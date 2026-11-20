package com.mrbubbletea.bubblesgunsmithing.item.gunPart.abstracts;


import com.mrbubbletea.bubblesgunsmithing.BubblesGunsmithing;
import com.mrbubbletea.bubblesgunsmithing.item.gunPart.GunPartType;
import net.minecraft.world.item.Item;


import java.io.IOException;
import java.util.ArrayList;




public abstract class AbstractGunPart extends Item {
    protected GunPartType type;
    protected ArrayList<GunPartEntry> entries = new ArrayList<>();
    protected GunPartStats stats;
    // Constructor
    public AbstractGunPart(Properties properties,GunPartType type, GunPartStats statValues) {
        super(properties);
        this.stats = GunPartStats.createGunPartStats();
    }


    // Creates an Attachment point for a Gun Part
    protected int createAttachmentPoint(String key, ArrayList<GunPartType> type, float x, float y, float z) {
        try {
            GunPartEntry entry = new GunPartEntry(key,type,x,y,z);
            boolean check = entries.add(entry);
            if (!check) {throw new IOException("No Entry was inserted into ArrayList");}
        } catch(IOException e) {
            BubblesGunsmithing.LOGGER.error("Error making attachment: ", e);
            return 0; // Failure, Cry and scream to the devs
        }
        return 1; // Successful creation;
    }
    /*------------GETTERS---------------------*/
    public GunPartType getGunPartType() {
        return type;
    }
    public String getGunPartTypeString() {
        return type.toString();
    }


    /*------------SETTERS---------------------*/


    // Helper inner class to store fields for gun part connection points
    protected static class GunPartEntry {
        // Fields
        private final String name;
        private final ArrayList<GunPartType> required;
        private AbstractGunPart attached;
        private final float x,y,z;
        // Constructors
        // For god knows what you want to use this constructor for
        protected GunPartEntry(String name, ArrayList<GunPartType> required) {
            this(name,required,0f,0f,0f);
        }
        // The values of the GunPart's Attachement point; The name of the point, the required part, and the render location on the model
        protected GunPartEntry(String name, ArrayList<GunPartType> required, float x, float y, float z) {
            this.name = name;
            this.required = new ArrayList<>(required);
            // Gun Part Attachments to a gun must be moved to a DataComponent since if it were left here it would give attachments to ALL gun items in a world
            //this.attached = attached;
            // Location on the gun the attachment point is located in
            this.x = x;
            this.y = y;
            this.z = z;
        }

        /*-------------------GETTERS---------------*/
        protected String getName() {
            return name;
        }
        protected ArrayList<GunPartType> getRequired() {
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
    public static class GunPartStats {
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

        // Thank god Intellij handles constructor gen
        // However I may need to replace with a builder :P
        public GunPartStats(double damageMelee, float damageMultiplier, double heatTolerance, double stressTolerance, double barrelStrength, float spread, float recoilVertical, float recoilHorizontal, float projectileSpeed, double reloadSpeed, double adsSpeed, int ammoCapacity, double weight, double totalLength, boolean needsWorkbench) {
            this.damageMelee = damageMelee;
            this.damageMultiplier = damageMultiplier;
            this.heatTolerance = heatTolerance;
            this.stressTolerance = stressTolerance;
            this.barrelStrength = barrelStrength;
            this.spread = spread;
            this.recoilVertical = recoilVertical;
            this.recoilHorizontal = recoilHorizontal;
            this.projectileSpeed = projectileSpeed;
            this.reloadSpeed = reloadSpeed;
            this.adsSpeed = adsSpeed;
            this.ammoCapacity = ammoCapacity;
            this.weight = weight;
            this.totalLength = totalLength;
            this.needsWorkbench = needsWorkbench;
        }

        /*-------------------GETTERS-------------------------*/
        protected double getDamageMelee() {
            return damageMelee;
        }

        protected float getDamageMultiplier() {
            return damageMultiplier;
        }

        protected double getHeatTolerance() {
            return heatTolerance;
        }

        protected double getStressTolerance() {
            return stressTolerance;
        }

        public double getBarrelStrength() {
            return barrelStrength;
        }

        public float getSpread() {
            return spread;
        }

        public float getRecoilVertical() {
            return recoilVertical;
        }

        public float getRecoilHorizontal() {
            return recoilHorizontal;
        }

        public float getProjectileSpeed() {
            return projectileSpeed;
        }

        public double getReloadSpeed() {
            return reloadSpeed;
        }

        public double getAdsSpeed() {
            return adsSpeed;
        }

        public int getAmmoCapacity() {
            return ammoCapacity;
        }

        public double getWeight() {
            return weight;
        }

        public double getTotalLength() {
            return totalLength;
        }

        public boolean isNeedsWorkbench() {
            return needsWorkbench;
        }

        /*-------------------SETTERS-------------------------*/

        public void setDamageMelee(double damageMelee) {
            this.damageMelee = damageMelee;
        }

        public void setDamageMultiplier(float damageMultiplier) {
            this.damageMultiplier = damageMultiplier;
        }

        public void setHeatTolerance(double heatTolerance) {
            this.heatTolerance = heatTolerance;
        }

        public void setStressTolerance(double stressTolerance) {
            this.stressTolerance = stressTolerance;
        }

        public void setBarrelStrength(double barrelStrength) {
            this.barrelStrength = barrelStrength;
        }

        public void setSpread(float spread) {
            this.spread = spread;
        }

        public void setRecoilVertical(float recoilVertical) {
            this.recoilVertical = recoilVertical;
        }

        public void setRecoilHorizontal(float recoilHorizontal) {
            this.recoilHorizontal = recoilHorizontal;
        }

        public void setProjectileSpeed(float projectileSpeed) {
            this.projectileSpeed = projectileSpeed;
        }

        public void setReloadSpeed(double reloadSpeed) {
            this.reloadSpeed = reloadSpeed;
        }

        public void setAdsSpeed(double adsSpeed) {
            this.adsSpeed = adsSpeed;
        }

        public void setAmmoCapacity(int ammoCapacity) {
            this.ammoCapacity = ammoCapacity;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public void setTotalLength(double totalLength) {
            this.totalLength = totalLength;
        }

        public void setNeedsWorkbench(boolean needsWorkbench) {
            this.needsWorkbench = needsWorkbench;
        }

        /*-------------------METHODS-------------------------*/


        // Factory Method to create GunPartStats instance
        protected static GunPartStats createGunPartStats() {
            return new GunPartStats();
        }
    }
}

