package com.mrbubbletea.bubblesgunsmithing.item.gunPart;

public enum GunPartType {
    BARREL("Barrel"),
    BARREL_ATTACHMENT("Barrel Attachment"),
    BOLT("Bolt"),
    GRIP("Grip"),
    GRIP_TAPE("Grip Tape"),
    HAMMER("Hammer"),
    HANDGUARD("Handguard"),
    MAGAZINE_WELL("Magazine Well"),
    MOUNT("Mount"),
    RECEIVER("Receiver"),
    SIGHT("Sight"),
    SLIDE("Slide"),
    STOCK("Stock"),
    TRIGGER("Trigger");


    public final String label;
    // Constructor
    private GunPartType(String label) {
        this.label = label;
    }

    // Shows the 'label' instead of the UPPERCASE name when name() is called
    @Override
    public String toString() {
        return this.label;
    }


}
