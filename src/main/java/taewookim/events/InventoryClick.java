package taewookim.events;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import taewookim.*;

public class InventoryClick implements Listener {

    @EventHandler
    public void inventoryclose(InventoryCloseEvent e) {
        if(e.getPlayer() instanceof Player p) {
            if(CustomMagica.opener.get(p)!=null) {
                InventoryOpener opener = CustomMagica.opener.get(p);
                switch(opener.type) {
                    case MAKEELEMENT:
                        ItemStack i1 = e.getInventory().getItem(10);
                        if(i1!=null) {
                            e.getPlayer().getInventory().addItem(i1);
                        }
                        ItemStack i2 = e.getInventory().getItem(11);
                        if(i2!=null) {
                            e.getPlayer().getInventory().addItem(i2);
                        }
                        break;
                    case MAKEMAGIC:
                        break;
                }
            }
            CustomMagica.opener.put(p, null);
        }
    }

    @EventHandler
    public void inventoryclick(InventoryClickEvent e) {
        if(e.getWhoClicked() instanceof Player p&&CustomMagica.opener.get(p)!=null) {
            e.setCancelled(true);
            InventoryOpener io = CustomMagica.opener.get(p);
            switch(io.type) {
                case MAKEELEMENT:
                    if(e.getCurrentItem()!=null) {
                        ItemStack i = e.getCurrentItem();
                        if(i.hasItemMeta()) {
                            ItemMeta m = i.getItemMeta();
                            if(e.getClickedInventory().getHolder()!=null) {
                                PersistentDataContainer per = m.getPersistentDataContainer();
                                if(per.has(CustomMagica.itemtypekey, PersistentDataType.STRING)) {
                                    String type = per.get(CustomMagica.itemtypekey, PersistentDataType.STRING);
                                    switch(type) {
                                        case "scroll":
                                        case "ingredient":
                                            ItemStack i11 = e.getInventory().getItem(11);
                                            if(i11!=null&& i11.hasItemMeta()) {
                                                ItemMeta m11 = i11.getItemMeta();
                                                PersistentDataContainer per11 = m11.getPersistentDataContainer();
                                                if(per11.has(CustomMagica.itemtypekey, PersistentDataType.STRING)
                                                        &&per11.get(CustomMagica.itemtypekey, PersistentDataType.STRING).equalsIgnoreCase(type)
                                                        &&((per11.has(CustomMagica.ingredientkey.get(0), PersistentDataType.STRING)&&per.has(CustomMagica.ingredientkey.get(0), PersistentDataType.STRING)&&per11.get(CustomMagica.ingredientkey.get(0), PersistentDataType.STRING).equalsIgnoreCase(per.get(CustomMagica.ingredientkey.get(0), PersistentDataType.STRING)))||(per11.has(CustomMagica.scrollkey.get(0), PersistentDataType.STRING)&&per.has(CustomMagica.scrollkey.get(0), PersistentDataType.STRING)&&per11.get(CustomMagica.scrollkey.get(0), PersistentDataType.STRING).equalsIgnoreCase(per.get(CustomMagica.scrollkey.get(0), PersistentDataType.STRING))))) {
                                                    int c = i11.getAmount();
                                                    if(c<5) {
                                                        c++;
                                                        i11.setAmount(c);
                                                        e.getInventory().setItem(11, i11);
                                                        i.setAmount(i.getAmount()-1);
                                                        e.getClickedInventory().setItem(e.getSlot(), i);
                                                    }
                                                }
                                            }else {
                                                ItemStack ci = i.clone();
                                                ci.setAmount(1);
                                                i.setAmount(i.getAmount()-1);
                                                e.getInventory().setItem(11, ci);
                                            }
                                            break;
                                        case "elementpiece":
                                        case "finalelement":
                                            ItemStack i10 = e.getInventory().getItem(10);
                                            if(i10==null||!i10.hasItemMeta()) {
                                                ItemStack ci = i.clone();
                                                ci.setAmount(1);
                                                i.setAmount(i.getAmount()-1);
                                                e.getInventory().setItem(10, ci);
                                            }
                                            break;
                                        case "magicbook":
                                            ItemStack i10f = e.getInventory().getItem(10);
                                            if(i10f==null||!i10f.hasItemMeta()) {
                                                ItemStack ci = i.clone();
                                                ci.setAmount(1);
                                                i.setAmount(i.getAmount()-1);
                                                e.getInventory().setItem(10, ci);
                                            }else {
                                                ItemMeta m10f = i10f.getItemMeta();
                                                PersistentDataContainer per10f = m10f.getPersistentDataContainer();
                                                if(per10f.has(CustomMagica.itemtypekey, PersistentDataType.STRING)
                                                        &&per10f.get(CustomMagica.itemtypekey, PersistentDataType.STRING).equalsIgnoreCase("magicbook")) {
                                                    ItemStack i11f = e.getInventory().getItem(11);
                                                    if(i11f==null||!i11f.hasItemMeta()) {
                                                        ItemStack cii = i.clone();
                                                        cii.setAmount(1);
                                                        i.setAmount(i.getAmount()-1);
                                                        e.getInventory().setItem(11, cii);
                                                    }
                                                }
                                            }
                                            break;
                                    }
                                }
                            }else {
                                int slot = e.getSlot();
                                if(slot==10||slot==11) {
                                    p.getInventory().addItem(i);
                                    e.getInventory().setItem(slot, new ItemStack(Material.AIR));
                                }else if(slot==16) {
                                    ItemStack i10 = e.getInventory().getItem(10);
                                    ItemStack i11 = e.getInventory().getItem(11);
                                    if(i10!=null&&i10.hasItemMeta()) {
                                        ItemMeta m10 = i10.getItemMeta();
                                        PersistentDataContainer per10 = m10.getPersistentDataContainer();
                                        if(per10.has(CustomMagica.itemtypekey, PersistentDataType.STRING)) {
                                            String string = per10.get(CustomMagica.itemtypekey, PersistentDataType.STRING);
                                            if(string.equalsIgnoreCase("elementpiece")) {
                                                if(i11!=null&&i11.hasItemMeta()) {
                                                    ItemMeta m11 = i11.getItemMeta();
                                                    PersistentDataContainer per11 = m11.getPersistentDataContainer();
                                                    if(per11.has(CustomMagica.itemtypekey, PersistentDataType.STRING)
                                                            &&per11.get(CustomMagica.itemtypekey, PersistentDataType.STRING).equalsIgnoreCase("ingredient")) {
                                                        int c = i11.getAmount();
                                                        ElementType type = ElementType.valueOf(per11.get(CustomMagica.ingredientkey.get(0), PersistentDataType.STRING));
                                                        p.getInventory().addItem(CraftingElement.valueOf(per10.get(CustomMagica.elementkey.get(0), PersistentDataType.STRING)).getItem(type, c));
                                                        e.getInventory().setItem(10, new ItemStack(Material.AIR));
                                                        e.getInventory().setItem(11, new ItemStack(Material.AIR));
                                                    }
                                                }else {
                                                    p.getInventory().addItem(CraftingElement.valueOf(per10.get(CustomMagica.elementkey.get(0), PersistentDataType.STRING)).getItem(ElementType.NONE, 0));
                                                    e.getInventory().setItem(10, new ItemStack(Material.AIR));
                                                    e.getInventory().setItem(11, new ItemStack(Material.AIR));
                                                }
                                            }else if(string.equalsIgnoreCase("finalelement")) {
                                                if(i11!=null&&i11.hasItemMeta()) {
                                                    ItemMeta m11 = i11.getItemMeta();
                                                    PersistentDataContainer per11 = m11.getPersistentDataContainer();
                                                    if(per11.has(CustomMagica.itemtypekey, PersistentDataType.STRING)
                                                            &&per11.get(CustomMagica.itemtypekey, PersistentDataType.STRING).equalsIgnoreCase("scroll")) {
                                                        p.getInventory().addItem(CraftingMagic.getMagic(i10, i11));
                                                        e.getInventory().setItem(10, new ItemStack(Material.AIR));
                                                        e.getInventory().setItem(11, new ItemStack(Material.AIR));
                                                    }
                                                }
                                            }else if(string.equalsIgnoreCase("magicbook")) {
                                                if(i11!=null&&i11.hasItemMeta()) {
                                                    ItemMeta m11 = i11.getItemMeta();
                                                    PersistentDataContainer per11 = m11.getPersistentDataContainer();
                                                    if(per11.has(CustomMagica.itemtypekey, PersistentDataType.STRING)
                                                            &&per11.get(CustomMagica.itemtypekey, PersistentDataType.STRING).equalsIgnoreCase("magicbook")) {
                                                        p.getInventory().addItem(CraftingMagic.getMagicwithAdd(i10, i11));
                                                        e.getInventory().setItem(10, new ItemStack(Material.AIR));
                                                        e.getInventory().setItem(11, new ItemStack(Material.AIR));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case MAKEMAGIC:
                    break;
            }
        }
    }
}
