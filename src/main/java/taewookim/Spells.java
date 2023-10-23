package taewookim;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import taewookim.magic.Spell;

import java.util.Arrays;

public enum Spells {

    None("없음", Spell.class);

    final String name;
    final Class<? extends Spell> clz;

    Spells(String name, Class<? extends Spell> clz) {
        this.name = name;
        this.clz = clz;
    }

    public ItemStack getItem() {
        ItemStack i = new ItemStack(Material.PAPER);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName("§c마법 스크롤");
        m.setLore(Arrays.asList("§7"+name));
        PersistentDataContainer container = m.getPersistentDataContainer();
        container.set(CustomMagica.scrollkey.get(0), PersistentDataType.STRING, this.toString());
        container.set(CustomMagica.itemtypekey, PersistentDataType.STRING, "scroll");
        i.setItemMeta(m);
        return i;
    }

    public Class<? extends Spell> getClz() {
        return clz;
    }

}
