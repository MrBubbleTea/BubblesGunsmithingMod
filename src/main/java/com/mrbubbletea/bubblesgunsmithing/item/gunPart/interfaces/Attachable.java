package com.mrbubbletea.bubblesgunsmithing.item.gunPart.interfaces;

import com.mrbubbletea.bubblesgunsmithing.item.gunPart.abstracts.AbstractGunPart;

public interface Attachable {
    // The Gun Part Attachable
    boolean isAttachable();
    AbstractGunPart attach();
}
