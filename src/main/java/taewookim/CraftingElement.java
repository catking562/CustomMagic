package taewookim;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public enum CraftingElement {

    NONE("§7무의 원소", Material.BLACK_TERRACOTTA), FIRE("§c불의 원소", Material.RED_DYE);

    final String name;
    final Material material;

    CraftingElement(String name, Material material) {
        this.name = name;
        this.material = material;
    }

    public ItemStack getItem(ElementType type, int strength) {
        ItemStack i = new ItemStack(material);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(name);
        m.setLore(Arrays.asList(type.name + strength + "개가 주입됨."));
        PersistentDataContainer per = m.getPersistentDataContainer();
        per.set(CustomMagica.itemtypekey, PersistentDataType.STRING, "finalelement");
        per.set(CustomMagica.ingredientkey.get(0), PersistentDataType.STRING, type.toString());
        per.set(CustomMagica.elementkey.get(0), PersistentDataType.STRING, this.toString());
        per.set(CustomMagica.ingredientsizekey.get(0), PersistentDataType.INTEGER, strength);
        i.setItemMeta(m);
        return i;
    }

}
