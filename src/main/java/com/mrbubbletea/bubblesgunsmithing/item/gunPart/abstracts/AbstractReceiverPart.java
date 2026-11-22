package com.mrbubbletea.bubblesgunsmithing.item.gunPart.abstracts;


import com.mrbubbletea.bubblesgunsmithing.item.gunPart.GunPartType;
import com.mrbubbletea.bubblesgunsmithing.item.gunPart.interfaces.ReceiverCore;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class AbstractReceiverPart extends AbstractGunPart implements ReceiverCore {

    public AbstractReceiverPart(Properties properties, ArrayList<GunPartEntry> entries, GunPartStats statValues) {
        super(properties, GunPartType.RECEIVER , entries, statValues);
    }


    // Left Click
    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        return super.useOn(context);
    }
}
