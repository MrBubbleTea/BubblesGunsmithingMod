package com.mrbubbletea.bubblesgunsmithing.item.gunPart.abstracts;

import com.mrbubbletea.bubblesgunsmithing.item.gunPart.GunPartType;
import com.mrbubbletea.bubblesgunsmithing.item.gunPart.interfaces.Attachable;

public class AbstractBarrelPart extends AbstractGunPart implements Attachable {

    public AbstractBarrelPart(Properties properties, GunPartType type, GunPartStats statValues) {
        super(properties, type, statValues);
    }

    @Override
    public boolean isAttachable() {
        return false;
    }

    @Override
    public AbstractGunPart attach() {
        return null;
    }
}
