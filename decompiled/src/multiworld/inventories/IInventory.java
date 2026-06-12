/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10630
 *  net.minecraft.class_1799
 *  net.minecraft.class_2371
 */
package multiworld.inventories;

import net.minecraft.class_10630;
import net.minecraft.class_1799;
import net.minecraft.class_2371;

public interface IInventory {
    public void mw$setItems(class_2371<class_1799> var1);

    public class_2371<class_1799> mw$getItems();

    public class_10630 mw$getEquipment();
}
