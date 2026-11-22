package com.mrbubbletea.bubblesgunsmithing.item.gunPart;

import com.mrbubbletea.bubblesgunsmithing.item.gunPart.abstracts.AbstractReceiverPart;
import com.mrbubbletea.bubblesgunsmithing.item.gunPart.interfaces.Reloadable;
import com.mrbubbletea.bubblesgunsmithing.item.gunPart.interfaces.Zoomable;

import java.util.ArrayList;

public class Receiver extends AbstractReceiverPart {

    public Receiver(Properties properties, ArrayList<GunPartEntry> gunPartEntries, GunPartStats gunPartStats) {
        super(properties, gunPartEntries,gunPartStats);
    }

    @Override
    public boolean isShootable() {
        return false;
    }

    @Override
    public void shoot() {

    }

    @Override
    public boolean isZoomable() {
        return false;
    }

    @Override
    public <T extends Zoomable> void zoom(T scope) {

    }

    @Override
    public boolean isReloadable() {
        return false;
    }

    @Override
    public <T extends Reloadable> void reload(T magWell) {

    }
}
