package taewookim;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class CraftingMagic {

    public static ItemStack getMagic(ItemStack finalelement, ItemStack scroll) {
        ItemStack i = new ItemStack(Material.BOOK);
        PersistentDataContainer per1 = finalelement.getItemMeta().getPersistentDataContainer();
        PersistentDataContainer per2 = scroll.getItemMeta().getPersistentDataContainer();
        ItemMeta m = i.getItemMeta();
        m.setDisplayName("§c미지의 마법서");
        PersistentDataContainer finalper = m.getPersistentDataContainer();
        finalper.set(CustomMagica.itemtypekey, PersistentDataType.STRING, "magicbook");
        finalper.set(CustomMagica.ingredientkey.get(0), PersistentDataType.STRING, per1.get(CustomMagica.ingredientkey.get(0), PersistentDataType.STRING));
        finalper.set(CustomMagica.elementkey.get(0), PersistentDataType.STRING, per1.get(CustomMagica.elementkey.get(0), PersistentDataType.STRING));
        finalper.set(CustomMagica.scrollkey.get(0), PersistentDataType.STRING, per2.get(CustomMagica.scrollkey.get(0), PersistentDataType.STRING));
        finalper.set(CustomMagica.ingredientsizekey.get(0), PersistentDataType.INTEGER, per1.get(CustomMagica.ingredientsizekey.get(0), PersistentDataType.INTEGER));
        finalper.set(CustomMagica.scrollsizekey.get(0), PersistentDataType.INTEGER, scroll.getAmount());
        finalper.set(CustomMagica.skillsizekey, PersistentDataType.INTEGER, 1);
        i.setItemMeta(m);
        return i;
    }

    public static ItemStack getMagicwithAdd(ItemStack magic1, ItemStack magic2) {
        ItemStack i = new ItemStack(Material.BOOK);
        PersistentDataContainer per1 = magic1.getItemMeta().getPersistentDataContainer();
        PersistentDataContainer per2 = magic2.getItemMeta().getPersistentDataContainer();
        ItemMeta m = i.getItemMeta();
        m.setDisplayName("§c미지의 마법서");
        PersistentDataContainer finalper = m.getPersistentDataContainer();
        finalper.set(CustomMagica.itemtypekey, PersistentDataType.STRING, "magicbook");
        int a = 0;
        for(int j = 0; j<per1.get(CustomMagica.skillsizekey, PersistentDataType.INTEGER); j++) {
            finalper.set(CustomMagica.ingredientkey.get(a), PersistentDataType.STRING, per1.get(CustomMagica.ingredientkey.get(j), PersistentDataType.STRING));
            finalper.set(CustomMagica.elementkey.get(a), PersistentDataType.STRING, per1.get(CustomMagica.elementkey.get(j), PersistentDataType.STRING));
            finalper.set(CustomMagica.scrollkey.get(a), PersistentDataType.STRING, per1.get(CustomMagica.scrollkey.get(j), PersistentDataType.STRING));
            finalper.set(CustomMagica.ingredientsizekey.get(a), PersistentDataType.INTEGER, per1.get(CustomMagica.ingredientsizekey.get(j), PersistentDataType.INTEGER));
            finalper.set(CustomMagica.scrollsizekey.get(a), PersistentDataType.INTEGER, per1.get(CustomMagica.scrollsizekey.get(j), PersistentDataType.INTEGER));
            a++;
        }
        for(int j = 0; j<per2.get(CustomMagica.skillsizekey, PersistentDataType.INTEGER); j++) {
            finalper.set(CustomMagica.ingredientkey.get(a), PersistentDataType.STRING, per2.get(CustomMagica.ingredientkey.get(j), PersistentDataType.STRING));
            finalper.set(CustomMagica.elementkey.get(a), PersistentDataType.STRING, per2.get(CustomMagica.elementkey.get(j), PersistentDataType.STRING));
            finalper.set(CustomMagica.scrollkey.get(a), PersistentDataType.STRING, per2.get(CustomMagica.scrollkey.get(j), PersistentDataType.STRING));
            finalper.set(CustomMagica.ingredientsizekey.get(a), PersistentDataType.INTEGER, per2.get(CustomMagica.ingredientsizekey.get(j), PersistentDataType.INTEGER));
            finalper.set(CustomMagica.scrollsizekey.get(a), PersistentDataType.INTEGER, per2.get(CustomMagica.scrollsizekey.get(j), PersistentDataType.INTEGER));
            a++;
        }
        finalper.set(CustomMagica.skillsizekey, PersistentDataType.INTEGER, a);
        i.setItemMeta(m);
        return i;
    }

}
