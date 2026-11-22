package com.mrbubbletea.bubblesgunsmithing.item.gunPart.abstracts;


import com.mrbubbletea.bubblesgunsmithing.BubblesGunsmithing;
import com.mrbubbletea.bubblesgunsmithing.item.gunPart.GunPartType;
import net.minecraft.world.item.Item;
import org.checkerframework.checker.units.qual.A;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.List.copyOf;


public abstract class AbstractGunPart extends Item {
    protected GunPartType type;
    protected ArrayList<GunPartEntry> entries = new ArrayList<>();
    protected GunPartStats stats;
    // Constructor
    public AbstractGunPart(Properties properties,GunPartType type, ArrayList<GunPartEntry> entries, GunPartStats statValues) {
        super(properties);
        this.type = type;
        this.entries.addAll(entries);
        this.stats = statValues;
    }


    // Creates an Attachment point for a Gun Part
    protected int createAttachmentPoint(String key, ArrayList<GunPartType> type, float x, float y, float z) {
        try {
            GunPartEntry entry = new GunPartEntry(key,x,y,z);
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
    public ArrayList<GunPartEntry> getEntries() {  return entries; }
    public GunPartStats getStats() { return stats; }

    /*--------------------------------------------EndOfAbstractGunPart--------------------------------------------------------------*/


    // Helper inner class to store fields for gun part connection points
    public static class GunPartEntry {
        // Fields
        private final String name;
        private final ArrayList<GunPartType> required = new ArrayList<>();
        private final float x,y,z;
        // Constructors
        // The values of the GunPart's Attachment point; The name of the point, the required part, and the render location on the model
        public GunPartEntry(String name, float x, float y, float z) {
            this.name = name;
            // Location on the gun the attachment point is located in
            this.x = x;
            this.y = y;
            this.z = z;
        }
        /*-------------------GETTERS---------------*/
        public String getName() {
            return name;
        }
        public ArrayList<GunPartType> getRequiredList() {
            return (ArrayList<GunPartType>) Collections.unmodifiableList(required);
        }
        public float getX() { return x;}
        public float getY() { return y;}
        public float getZ() { return z;}
        public float[] getXYZ() { return new float[]{getX(),getY(),getZ()}; }
        /*-------------------SETTERS---------------*/
        public boolean checkValidGunPart(AbstractGunPart newPart) {
            if (newPart == null) {return false;}
            return required.contains(newPart.type);
        }

        public static ArrayList<GunPartEntry> createEntryList(GunPartEntry... entries) {
            return new ArrayList<>(Arrays.asList(entries));
        }

        private void addGunPartType(GunPartType type) {
            required.add(type);
        }


        public static GunPartEntry createGunPartEntry(String name, float x, float y, float z, GunPartType... types) {
            GunPartEntry entry = new GunPartEntry(name, x, y, z);
            for (GunPartType type : types) {
                entry.addGunPartType(type);
            }
            return entry;
        }
    }

    // Inner Protected class that carries the attachments stats
    public static class GunPartStats {
        // Damage
        double damageMelee; // Melee Damage
        float damageMultiplier; // Ranged Damage Multiplier
        // Durability Modifiers
        double heatTolerance; // How much tolerance a gun part can protect itself from sustained fire
        double stressTolerance; // How much tolerance a gun part can protect itself from cartridge operations and such
        double barrelStrength; // How strong the barrel can handle for a round; if too low, gun MAY explode.
        // Accuracy
        float spread; // How accurate is the projectiles from the center of aim
        float recoilVertical; // Vertical Recoil
        float recoilHorizontal; // Horizontal Recoil
        // Speed
        float projectileSpeed; // How fast the projectile leaves the gun
        double reloadSpeed; // How fast can the player can reload the gun
        double adsSpeed; // How fast the player can aim down sights
        // Capacity
        int ammoCapacity; // How much ammo can the part hold if applicable; defaults to zero
        double weight; // How heavy the gun weighs, higher values = slower player movement speed
        // Length
        double totalLength; // Total length of the GunPart, gun MAY bump into things
        // Booleans
        boolean needsWorkbench; // Does the GunPart require a workbench to attach it


        // Thank god Intellij handles constructor gen
        // However I may need to replace with a builder :P
        public GunPartStats(GunPartStatsBuilder builder) {
            this.damageMelee = builder.damageMelee;
            this.damageMultiplier = builder.damageMultiplier;
            this.heatTolerance = builder.heatTolerance;
            this.stressTolerance = builder.stressTolerance;
            this.barrelStrength = builder.barrelStrength;
            this.spread = builder.spread;
            this.recoilVertical = builder.recoilVertical;
            this.recoilHorizontal = builder.recoilHorizontal;
            this.projectileSpeed = builder.projectileSpeed;
            this.reloadSpeed = builder.reloadSpeed;
            this.adsSpeed = builder.adsSpeed;
            this.ammoCapacity = builder.ammoCapacity;
            this.weight = builder.weight;
            this.totalLength = builder.totalLength;
            this.needsWorkbench = builder.needsWorkbench;
        }

        /*-------------------GETTERS-------------------------*/
        public double getDamageMelee() {
            return damageMelee;
        }
        public float getDamageMultiplier() {
            return damageMultiplier;
        }
        public double getHeatTolerance() {
            return heatTolerance;
        }
        public double getStressTolerance() {
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

        public static class GunPartStatsBuilder {
            // Damage
            private double damageMelee = 0.0; // Melee Damage
            private float damageMultiplier = 0f; // Ranged Damage Multiplier
            // Durability Modifiers
            private double heatTolerance = 0.0; // How much tolerance a gun part can protect itself from sustained fire
            private double stressTolerance = 0.0; // How much tolerance a gun part can protect itself from cartridge operations and such
            private double barrelStrength = 0.0; // How strong the barrel can handle for a round; if too low, gun MAY explode.
            // Accuracy
            private float spread = 0f; // How accurate is the projectiles from the center of aim
            private float recoilVertical = 0f; // Vertical Recoil
            private float recoilHorizontal = 0f; // Horizontal Recoil
            // Speed
            private float projectileSpeed = 0f; // How fast the projectile leaves the gun
            private double reloadSpeed = 0.0; // How fast can the player can reload the gun
            private double adsSpeed = 0.0; // How fast the player can aim down sights
            // Capacity
            private int ammoCapacity = 0; // How much ammo can the part hold if applicable; defaults to zero
            private double weight = 0.0; // How heavy the gun weighs, higher values = slower player movement speed
            // Length
            private double totalLength = 0.0; // Total length of the GunPart, gun MAY bump into things
            // Booleans
            private boolean needsWorkbench = true; // Does the GunPart require a workbench to attach it

            /*-------------------SETTERS-------------------------*/
            public GunPartStatsBuilder setDamageMelee(double damageMelee) {
                this.damageMelee = damageMelee;
                return this;
            }
            public GunPartStatsBuilder setDamageMultiplier(float damageMultiplier) {
                this.damageMultiplier = damageMultiplier;
                return this;
            }
            public GunPartStatsBuilder setHeatTolerance(double heatTolerance) {
                this.heatTolerance = heatTolerance;
                return this;
            }
            public GunPartStatsBuilder setStressTolerance(double stressTolerance) {
                this.stressTolerance = stressTolerance;
                return this;
            }
            public GunPartStatsBuilder setBarrelStrength(double barrelStrength) {
                this.barrelStrength = barrelStrength;
                return this;
            }
            public GunPartStatsBuilder setSpread(float spread) {
                this.spread = spread;
                return this;
            }
            public GunPartStatsBuilder setRecoilVertical(float recoilVertical) {
                this.recoilVertical = recoilVertical;
                return this;
            }
            public GunPartStatsBuilder setRecoilHorizontal(float recoilHorizontal) {
                this.recoilHorizontal = recoilHorizontal;
                return this;
            }
            public GunPartStatsBuilder setProjectileSpeed(float projectileSpeed) {
                this.projectileSpeed = projectileSpeed;
                return this;
            }
            public GunPartStatsBuilder setReloadSpeed(double reloadSpeed) {
                this.reloadSpeed = reloadSpeed;
                return this;
            }
            public GunPartStatsBuilder setAdsSpeed(double adsSpeed) {
                this.adsSpeed = adsSpeed;
                return this;
            }
            public GunPartStatsBuilder setAmmoCapacity(int ammoCapacity) {
                this.ammoCapacity = ammoCapacity;
                return this;
            }
            public GunPartStatsBuilder setWeight(double weight) {this.weight = weight; return this;}
            public GunPartStatsBuilder setTotalLength(double totalLength) {
                this.totalLength = totalLength;
                return this;
            }
            public GunPartStatsBuilder setNeedsWorkbench(boolean needsWorkbench) {
                this.needsWorkbench = needsWorkbench;
                return this;
            }

            public GunPartStatsBuilder () {};
            public GunPartStats build() {
                return new GunPartStats(this);
            }
        }



        /*-------------------METHODS-------------------------*/


//        // Factory Method to create GunPartStats instance
//        protected static GunPartStats createGunPartStats() {
//            return new GunPartStats();
//        }
    }
}

