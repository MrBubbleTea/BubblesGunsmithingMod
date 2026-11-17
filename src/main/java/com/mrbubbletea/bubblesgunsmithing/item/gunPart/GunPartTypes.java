package com.mrbubbletea.bubblesgunsmithing.item.gunPart;

public enum GunPartTypes {
    BARREL("Barrel"),
    BOLT("Bolt"),
    GRIP("Grip"),
    GRIPTAPE("Grip Tape"),
    HAMMER("Hammer"),
    HANDGUARD("Handguard"),
    RAIL("Rail"),
    RECEIVER("Receiver"),
    SIGHT("Sight"),
    SLIDE("Slide"),
    STOCK("Stock"),
    TRIGGER("Trigger");


    public final String label;
    // Constructor
    private GunPartTypes(String label) {
        this.label = label;
    }

    // Shows the 'label' instead of the UPPERCASE name when name() is called
    @Override
    public String toString() {
        return this.label;
    }
}
