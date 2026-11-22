package com.mrbubbletea.bubblesgunsmithing.item;

import com.mrbubbletea.bubblesgunsmithing.BubblesGunsmithing;
import com.mrbubbletea.bubblesgunsmithing.item.gunPart.GunPartType;
import com.mrbubbletea.bubblesgunsmithing.item.gunPart.Receiver;
import com.mrbubbletea.bubblesgunsmithing.item.gunPart.abstracts.AbstractGunPart;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;

public class ModItems {
    // Create a Deferred Register to hold Items which will all be registered under the "bubblesgunsmithing" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BubblesGunsmithing.MOD_ID);


    /*-----------------------------------ITEMS---------------------------------------*/
    public static final DeferredItem<Item> IRON_RECEIVER = ITEMS.register("iron_receiver", () -> new Receiver(
            new Item.Properties()
                .rarity(Rarity.UNCOMMON)
                .durability(250)
            ,
            AbstractGunPart.GunPartEntry.createEntryList( // Var-Arg of Entries to create the list of attachment points
                    AbstractGunPart.GunPartEntry.createGunPartEntry("Upper Rail", 1,1,1, // Var-arg of Types to create the Entry
                            GunPartType.SIGHT,
                            GunPartType.MOUNT
                            ),
                    AbstractGunPart.GunPartEntry.createGunPartEntry("Lower receiver Front", 2,2,2, // Var-arg of Types to create the Entry
                            GunPartType.GRIP,
                            GunPartType.MAGAZINE_WELL
                    ),
                    AbstractGunPart.GunPartEntry.createGunPartEntry("Lower receiver Back", 2,2,2, // Var-arg of Types to create the Entry
                            GunPartType.GRIP,
                            GunPartType.MAGAZINE_WELL
                    ),
                    AbstractGunPart.GunPartEntry.createGunPartEntry("Buffer", 2,2,2, // Var-arg of Types to create the Entry
                            GunPartType.STOCK
                    ),
                    AbstractGunPart.GunPartEntry.createGunPartEntry("Rifling", 2,2,2, // Var-arg of Types to create the Entry
                            GunPartType.BARREL
                    )
            ),
            new AbstractGunPart.GunPartStats.GunPartStatsBuilder() // Sets the base stats
                    .setAdsSpeed(1.0)
                    .setDamageMelee(1.0)
                    .setDamageMultiplier(1f)
                    .setSpread(100f)
                    .setRecoilHorizontal(100f)
                    .setRecoilVertical(100f)
                    .build()
            )
    );

    /*-----------------------------------FOOD ITEMS---------------------------------------*/
    // Creates a new food item with the id "bubblesgunsmithing:example_id", nutrition 1 and saturation 2
    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(
            new Item.Properties()
                .food(new FoodProperties.Builder()
                    .alwaysEdible()
                    .nutrition(1)
                    .saturationModifier(2f)
                    .build()
                )
            )
    );




    public static void register(IEventBus eventBus) {
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(eventBus);
    }

}
