package com.mrbubbletea.bubblesgunsmithing.item.gunPart.abstracts;


import com.mrbubbletea.bubblesgunsmithing.BubblesGunsmithing;
import com.mrbubbletea.bubblesgunsmithing.item.gunPart.GunPartTypes;


import java.io.IOException;
import java.util.ArrayList;




public abstract class AbstractGunPart {
    protected GunPartTypes type;
    protected ArrayList<GunAttachmentEntry> entries;

    protected int createAttachmentPoint(String key, GunPartTypes type, AbstractGunPart value) {
        try {
            GunAttachmentEntry entry = new GunAttachmentEntry(key,type,value);
            entries.add(entry);
            if (!entries.contains(entry)) {throw new IOException("No Entry");}
        } catch(IOException e) {
            BubblesGunsmithing.LOGGER.error("Error making attachment: ", e);
            return 0;
        }
        return 1; //successful creation;
    }
    protected void attach() {

    }
    // Helper class to store fields of Triples
    protected static class GunAttachmentEntry {
        private final String name;
        private final GunPartTypes required;
        private AbstractGunPart attached;
        protected GunAttachmentEntry(String name, GunPartTypes required, AbstractGunPart attached) {
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
        // Returns the previous attachment if applicable
        protected AbstractGunPart setAttachment(AbstractGunPart newAttachment) {
            AbstractGunPart toReturn = attached;
            attached = newAttachment;
            return toReturn;
        }

    }
}

