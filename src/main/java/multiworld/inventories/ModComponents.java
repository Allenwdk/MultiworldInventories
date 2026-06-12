package multiworld.inventories;

import net.minecraft.class_2960;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistryV3;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class ModComponents implements EntityComponentInitializer {
    public static final String ID = "perworldinv";
    public static final ComponentKey<PerWorldInventoryComponent> PER_WORLD_INV = ComponentRegistryV3.INSTANCE
            .getOrCreate(class_2960.method_60655("perworldinv", "per_world_inventory"), PerWorldInventoryComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(PER_WORLD_INV, player -> new PerWorldInventoryComponent(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}
