package multiworld.inventories.mixin;

import multiworld.inventories.ModComponents;
import multiworld.inventories.PerWorldInventoryComponent;
import net.minecraft.class_3222;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_3222.class)
public class PlayerMixin {
    @Inject(method = "method_14203", at = @At("TAIL"))
    private void mw_inv$copyPerWorldInventory(class_3222 oldPlayer, boolean alive, CallbackInfo ci) {
        System.out.println("Restoring Inv..");
        class_3222 newPlayer = (class_3222) (Object) this;
        PerWorldInventoryComponent oldComp = ModComponents.PER_WORLD_INV.get(oldPlayer);
        PerWorldInventoryComponent newComp = ModComponents.PER_WORLD_INV.get(newPlayer);
        newComp.copyFrom(oldComp);
    }
}
