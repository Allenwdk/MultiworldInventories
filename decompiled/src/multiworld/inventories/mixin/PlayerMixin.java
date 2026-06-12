/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1937
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_5321
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package multiworld.inventories.mixin;

import java.util.Set;
import multiworld.inventories.ModComponents;
import multiworld.inventories.PerWorldInventoryComponent;
import net.minecraft.class_1937;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_5321;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_3222.class})
public class PlayerMixin {
    @Inject(method={"method_14203"}, at={@At(value="TAIL")})
    private void mw_inv$copyPerWorldInventory(class_3222 oldPlayer, boolean alive, CallbackInfo ci) {
        System.out.println("Restoring Inv..");
        class_3222 newPlayer = (class_3222)this;
        PerWorldInventoryComponent oldComp = (PerWorldInventoryComponent)ModComponents.PER_WORLD_INV.get((Object)oldPlayer);
        PerWorldInventoryComponent newComp = (PerWorldInventoryComponent)ModComponents.PER_WORLD_INV.get((Object)newPlayer);
        newComp.copyFrom(oldComp);
    }

    @Inject(method={"Lnet/minecraft/class_3222;method_48105(Lnet/minecraft/class_3218;DDDLjava/util/Set;FFZ)Z"}, at={@At(value="HEAD")})
    private void mw_inv$onTeleport_saveInv(class_3218 newLevel, double x, double y, double z, Set a, float yaw, float pitch, boolean zz, CallbackInfoReturnable ci) {
        class_3222 player = (class_3222)this;
        PerWorldInventoryComponent comp = (PerWorldInventoryComponent)ModComponents.PER_WORLD_INV.get((Object)player);
        comp.saveInventory((class_5321<class_1937>)player.method_51469().method_27983(), player.method_31548());
    }

    @Inject(method={"Lnet/minecraft/class_3222;method_48105(Lnet/minecraft/class_3218;DDDLjava/util/Set;FFZ)Z"}, at={@At(value="TAIL")})
    private void mw_inv$onTeleport_loadInv(class_3218 newLevel, double x, double y, double z, Set a, float yaw, float pitch, boolean zz, CallbackInfoReturnable ci) {
        class_3222 newPlayer = (class_3222)newLevel.method_18470(((class_3222)this).method_5667());
        PerWorldInventoryComponent comp = (PerWorldInventoryComponent)ModComponents.PER_WORLD_INV.get((Object)newPlayer);
        comp.loadInventory((class_5321<class_1937>)newLevel.method_27983(), newPlayer.method_31548());
    }
}
