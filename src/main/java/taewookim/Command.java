package taewookim;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        switch(strings.length) {
            case 0:
                commandSender.sendMessage("/magica getitem <scroll/elementpiece/ingredient> <name>");
                commandSender.sendMessage("/magica opengui <makeelement/makemagic>");
                break;
            case 1:
                switch(strings[0]) {
                    case "getitem":
                        commandSender.sendMessage("/magica getitem <scroll/elementpiece/ingredient> <name>");
                        break;
                    case "opengui":
                        commandSender.sendMessage("/magica opengui <makeelement/makemagic>");
                        break;
                    default:
                        commandSender.sendMessage("?");
                        break;
                }
                break;
            case 2:
                switch(strings[0]) {
                    case "getitem":
                        switch(strings[1]) {
                            case "scroll":
                                commandSender.sendMessage("/magica getitem scroll <name>");
                                break;
                            case "elementpiece":
                                commandSender.sendMessage("/magica getitem elementpiece <name>");
                                break;
                            case "ingredient":
                                commandSender.sendMessage("/magica getitem ingredient <name>");
                                break;
                        }
                        break;
                    case "opengui":
                        switch(strings[1]) {
                            case "makeelement":
                                if(commandSender instanceof Player p) {
                                    Inventory inv = Bukkit.createInventory(null, 27, "원소 만들기");
                                    ItemStack it = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                                    ItemMeta m = it.getItemMeta();
                                    m.setDisplayName(" ");
                                    it.setItemMeta(m);
                                    for(int i = 0;i<27;i++) {
                                        inv.setItem(i, it);
                                    }
                                    inv.setItem(10, new ItemStack(Material.AIR));
                                    inv.setItem(11, new ItemStack(Material.AIR));
                                    it = new ItemStack(Material.BARRIER);
                                    m = it.getItemMeta();
                                    m.setDisplayName("§c만들 수 없음.");
                                    it.setItemMeta(m);
                                    inv.setItem(16, it);
                                    p.openInventory(inv);
                                    CustomMagica.opener.put(p, new InventoryOpener(InventoryType.MAKEELEMENT));
                                }
                                break;
                            case "makemagic":
                                if(commandSender instanceof Player p) {
                                    Inventory inv = Bukkit.createInventory(null, 27, "마법 만들기");
                                    ItemStack it = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                                    ItemMeta m = it.getItemMeta();
                                    m.setDisplayName(" ");
                                    it.setItemMeta(m);
                                    for(int i = 0;i<27;i++) {
                                        inv.setItem(i, it);
                                    }
                                    inv.setItem(10, new ItemStack(Material.AIR));
                                    inv.setItem(11, new ItemStack(Material.AIR));
                                    it = new ItemStack(Material.BARRIER);
                                    m = it.getItemMeta();
                                    m.setDisplayName("§c만들 수 없음.");
                                    it.setItemMeta(m);
                                    inv.setItem(16, it);
                                    p.openInventory(inv);
                                    CustomMagica.opener.put(p, new InventoryOpener(InventoryType.MAKEMAGIC));
                                }
                                break;
                        }
                        break;
                    default:
                        commandSender.sendMessage("?");
                        break;
                }
                break;
            default:
                switch(strings[0]) {
                    case "getitem":
                        switch(strings[1]) {
                            case "scroll":
                                if(commandSender instanceof Player p) {
                                    try{
                                        p.getInventory().addItem(Spells.valueOf(strings[2]).getItem());
                                    }catch(Exception e) {
                                    }
                                }
                                break;
                            case "elementpiece":
                                if(commandSender instanceof Player p) {
                                    try{
                                        p.getInventory().addItem(Elements.valueOf(strings[2]).getItem());
                                    }catch(Exception e) {
                                    }
                                }
                                break;
                            case "ingredient":
                                if(commandSender instanceof Player p) {
                                    try{
                                        p.getInventory().addItem(ElementType.valueOf(strings[2]).getItem());
                                    }catch(Exception e) {
                                    }
                                }
                                break;
                        }
                        break;
                    case "opengui":
                        switch(strings[1]) {
                            case "makeelement":
                                if(commandSender instanceof Player p) {
                                    Inventory inv = Bukkit.createInventory(null, 27, "원소 만들기");
                                    ItemStack it = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                                    ItemMeta m = it.getItemMeta();
                                    m.setDisplayName(" ");
                                    it.setItemMeta(m);
                                    for(int i = 0;i<27;i++) {
                                        inv.setItem(i, it);
                                    }
                                    inv.setItem(10, new ItemStack(Material.AIR));
                                    inv.setItem(11, new ItemStack(Material.AIR));
                                    it = new ItemStack(Material.BARRIER);
                                    m = it.getItemMeta();
                                    m.setDisplayName("§c만들 수 없음.");
                                    it.setItemMeta(m);
                                    inv.setItem(16, it);
                                    p.openInventory(inv);
                                    CustomMagica.opener.put(p, new InventoryOpener(InventoryType.MAKEELEMENT));
                                }
                                break;
                            case "makemagic":
                                if(commandSender instanceof Player p) {
                                    Inventory inv = Bukkit.createInventory(null, 27, "마법 만들기");
                                    ItemStack it = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                                    ItemMeta m = it.getItemMeta();
                                    m.setDisplayName(" ");
                                    it.setItemMeta(m);
                                    for(int i = 0;i<27;i++) {
                                        inv.setItem(i, it);
                                    }
                                    inv.setItem(10, new ItemStack(Material.AIR));
                                    inv.setItem(11, new ItemStack(Material.AIR));
                                    it = new ItemStack(Material.BARRIER);
                                    m = it.getItemMeta();
                                    m.setDisplayName("§c만들 수 없음.");
                                    it.setItemMeta(m);
                                    inv.setItem(16, it);
                                    p.openInventory(inv);
                                    CustomMagica.opener.put(p, new InventoryOpener(InventoryType.MAKEMAGIC));
                                }
                                break;
                        }
                        break;
                    default:
                        commandSender.sendMessage("?");
                        break;
                }
                break;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        switch(strings.length) {
            case 1:
                return Arrays.asList("getitem", "opengui");
            case 2:
                switch(strings[0]) {
                    case "getitem":
                        return Arrays.asList("scroll", "elementpiece", "ingredient");
                    case "opengui":
                        return Arrays.asList("makeelement", "makemagic");
                }
                break;
            case 3:
                switch(strings[0]) {
                    case "getitem":
                        ArrayList<String> list = new ArrayList<>();
                        switch(strings[1]) {
                            case "scroll":
                                for(Spells spells : Spells.values()) {
                                    list.add(spells.toString());
                                }
                                break;
                            case "elementpiece":
                                for(Elements elements : Elements.values()) {
                                    list.add(elements.toString());
                                }
                                break;
                            case "ingredient":
                                for(ElementType elements : ElementType.values()) {
                                    list.add(elements.toString());
                                }
                                break;
                        }
                        return list;
                }
                break;
        }
        return Arrays.asList();
    }

}
