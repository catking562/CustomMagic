package taewookim;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import taewookim.elements.Element;
import taewookim.elements.Element_Block;
import taewookim.elements.Element_Normal;

import java.util.Arrays;

public enum ElementType {

    NONE("§b영혼", Material.SOUL_SAND, Element.class), NORMAL("§c피", Material.REDSTONE, Element_Normal.class), BLOCK("§6흙", Material.DIRT, Element_Block.class);

    final String name;
    final Material material;
    final Class<? extends Element> clz;

    ElementType(String name, Material material, Class<? extends Element> clz) {
        this.name = name;
        this.material = material;
        this.clz = clz;
    }

    public ItemStack getItem() {
        ItemStack i = new ItemStack(material);
        ItemMeta m = i.getItemMeta();
        PersistentDataContainer per = m.getPersistentDataContainer();
        per.set(CustomMagica.ingredientkey.get(0), PersistentDataType.STRING, this.toString());
        per.set(CustomMagica.itemtypekey, PersistentDataType.STRING, "ingredient");
        m.setDisplayName(name);
        m.setLore(Arrays.asList("§f[ §5마법재료 §f]"));
        i.setItemMeta(m);
        return i;
    }

    public Class<? extends Element> getClz() {
        return clz;
    }

}
