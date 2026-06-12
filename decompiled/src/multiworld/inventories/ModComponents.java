/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  org.ladysnake.cca.api.v3.component.ComponentKey
 *  org.ladysnake.cca.api.v3.component.ComponentRegistryV3
 *  org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry
 *  org.ladysnake.cca.api.v3.entity.EntityComponentInitializer
 *  org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy
 */
package multiworld.inventories;

import multiworld.inventories.PerWorldInventoryComponent;
import net.minecraft.class_2960;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistryV3;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class ModComponents
implements EntityComponentInitializer {
    public static final String ID = "perworldinv";
    public static final ComponentKey<PerWorldInventoryComponent> PER_WORLD_INV = ComponentRegistryV3.INSTANCE.getOrCreate(class_2960.method_60655((String)"perworldinv", (String)"per_world_inventory"), PerWorldInventoryComponent.class);

    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(PER_WORLD_INV, player -> new PerWorldInventoryComponent(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}
