package com.mrbubbletea.bubblesgunsmithing.item.gunPart;

public enum GunPartTypes {
    RECEIVER("Receiver"),
    TRIGGER("Trigger"),
    BOLT("Bolt"),
    SLIDE("Slide"),
    HAMMER("Hammer"),
    RAIL("Rail"),
    GRIP("Grip");

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
