package com.mrbubbletea.bubblesgunsmithing.item.gunPart.interfaces;

import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;

public interface Zoomable {
    // Modifies the Players FOV
    void zoom(ComputeFovModifierEvent event);
}
