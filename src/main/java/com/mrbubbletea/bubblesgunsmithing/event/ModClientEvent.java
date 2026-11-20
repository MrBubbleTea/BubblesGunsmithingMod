package com.mrbubbletea.bubblesgunsmithing.event;

import com.mrbubbletea.bubblesgunsmithing.BubblesGunsmithing;
import com.mrbubbletea.bubblesgunsmithing.item.ModItems;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = BubblesGunsmithing.MOD_ID, value = Dist.CLIENT)
public class ModClientEvent {
    @SubscribeEvent
    public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
        if (event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() == ModItems.EXAMPLE_ITEM.get()) {
            float fovModifier = 1f;
            event.setNewFovModifier(fovModifier);
        }
    }
    @SubscribeEvent
    public static void onLeftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {
        if (event.getItemStack().is(ModItems.EXAMPLE_ITEM.get())) {
            //
        }
    }
}
