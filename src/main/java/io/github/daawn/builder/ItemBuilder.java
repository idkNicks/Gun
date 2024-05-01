package io.github.daawn.builder;

import com.google.common.base.Preconditions;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemBuilder(ItemStack item) {
        this.itemStack = item;
        this.itemMeta = item.getItemMeta();
    }

    public ItemBuilder(Material material) {
        this(material, 1);
    }

    public ItemBuilder(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setName(String name) {
        Preconditions.checkNotNull(name, "The name cannot be null.");
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        List<String> coloredLore = lore.stream()
                .map(line -> ChatColor.translateAlternateColorCodes('&', line))
                .collect(Collectors.toList());
        itemMeta.setLore(coloredLore);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        return setLore(Arrays.asList(lore));
    }

    public ItemBuilder setCustomModelData(int id) {
        itemMeta.setCustomModelData(id);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        Preconditions.checkArgument(enchantment != null, "Enchantment cannot be null.");
        Preconditions.checkArgument(level >= 1, "Enchantment level cannot be less than 1.");
        itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder hideAttributes() {
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        return this;
    }

    public ItemBuilder setOwner(UUID owner) {
        Preconditions.checkNotNull(owner, "Owner UUID cannot be null.");
        Preconditions.checkArgument(itemMeta instanceof SkullMeta, "ItemMeta must be an instance of SkullMeta.");
        OfflinePlayer player = Bukkit.getOfflinePlayer(owner);
        ((SkullMeta) itemMeta).setOwningPlayer(player);
        return this;
    }

    public ItemBuilder setColor(Color color) {
        if (itemMeta instanceof LeatherArmorMeta) ((LeatherArmorMeta) itemMeta).setColor(color);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}