package com.mrbubbletea.bubblesgunsmithing.component;

import com.mrbubbletea.bubblesgunsmithing.BubblesGunsmithing;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.UnaryOperator;

public class ModDataComponent {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE,BubblesGunsmithing.MOD_ID);

    //public static final DeferredHolder<DataComponentType<?>, DataComponentType<>>


    private static <T>DeferredHolder<DataComponentType<?>,DataComponentType<T>> register(String name,
                                                                                         UnaryOperator<DataComponentType.Builder<T>> builderUnaryOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderUnaryOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
