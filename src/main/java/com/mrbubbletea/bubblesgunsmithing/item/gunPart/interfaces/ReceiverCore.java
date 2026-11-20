package com.mrbubbletea.bubblesgunsmithing.item.gunPart.interfaces;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;

public interface ReceiverCore {
    InteractionResult useOn(UseOnContext context);
    boolean isShootable();
    void shoot();
    boolean isZoomable();
    <T extends Zoomable> void zoom(T scope); // Defines that, whatever class that implements Zoomable, call its zoom method;
    boolean isReloadable();
    <T extends Reloadable> void reload(T magWell);
}
