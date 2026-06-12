/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10630
 *  net.minecraft.class_1661
 *  net.minecraft.class_1799
 *  net.minecraft.class_2371
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 */
package multiworld.inventories.mixin;

import multiworld.inventories.IInventory;
import net.minecraft.class_10630;
import net.minecraft.class_1661;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={class_1661.class})
public class InventoryAccessor
implements IInventory {
    @Shadow
    @Final
    @Mutable
    public class_2371<class_1799> field_7547;
    @Shadow
    @Final
    @Mutable
    private class_10630 field_56552;

    @Override
    public void mw$setItems(class_2371<class_1799> items) {
        this.field_7547 = items;
    }

    @Override
    public class_2371<class_1799> mw$getItems() {
        return this.field_7547;
    }

    @Override
    public class_10630 mw$getEquipment() {
        return this.field_56552;
    }
}
