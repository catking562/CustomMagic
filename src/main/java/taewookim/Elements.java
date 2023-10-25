package taewookim;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public enum Elements {

    NONE("§7무의 원소 조각", Material.BLACK_DYE), FIRE("§c불의 원소 조각", Material.FIRE_CHARGE),
    WATER("§b물의 원소 조각", Material.TUBE_CORAL_FAN)
    ;

    final String name;
    final Material material;

    Elements(String name, Material material) {
        this.name = name;
        this.material = material;
    }

    public ItemStack getItem() {
        ItemStack i = new ItemStack(material);
        ItemMeta m = i.getItemMeta();
        PersistentDataContainer per = m.getPersistentDataContainer();
        per.set(CustomMagica.elementkey.get(0), PersistentDataType.STRING, this.toString());
        per.set(CustomMagica.itemtypekey, PersistentDataType.STRING, "elementpiece");
        m.setDisplayName(name);
        i.setItemMeta(m);
        return i;
    }

}
