package io.github.daawn.nbt;

import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NBTItem extends NBTCompound {

    private final ItemStack itemStack;

    public NBTItem(ItemStack item) {
        this.itemStack = item;
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        this.compoundTag = nmsItem.getOrCreateTag();
    }

    public void applyChanges() {
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        nmsItem.setTag(compoundTag);
        ItemStack craftItem = CraftItemStack.asBukkitCopy(nmsItem);
        this.itemStack.setItemMeta(craftItem.getItemMeta());
    }

    public ItemStack getItem() {
        applyChanges();
        return itemStack;
    }
}