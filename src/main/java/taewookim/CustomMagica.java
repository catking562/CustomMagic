package taewookim;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.units.qual.N;
import taewookim.events.InventoryClick;
import taewookim.events.UseSkill;
import taewookim.magic.Spell;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomMagica extends JavaPlugin {

    public static Map<Player, InventoryOpener> opener = new HashMap<>();
    public static ArrayList<Entity> displayerentities = new ArrayList<>();
    public static ArrayList<Spell> updatingspells = new ArrayList<>();
    public static ArrayList<Spell> addingspells = new ArrayList<>();
    public static boolean isupdating = false;

    public static NamespacedKey itemtypekey;
    public static NamespacedKey skillsizekey;
    public static ArrayList<NamespacedKey> scrollkey = new ArrayList<>();
    public static ArrayList<NamespacedKey> elementkey = new ArrayList<>();
    public static ArrayList<NamespacedKey> ingredientkey = new ArrayList<>();
    public static ArrayList<NamespacedKey> ingredientsizekey = new ArrayList<>();
    public static ArrayList<NamespacedKey> scrollsizekey = new ArrayList<>();

    public void keyLoad() {
        itemtypekey = new NamespacedKey(this, "itemtype");
        skillsizekey = new NamespacedKey(this, "skillsize");
        for(int i = 0; i<100; i++) {
            scrollkey.add(new NamespacedKey(this, "scrolltype" + i));
            elementkey.add(new NamespacedKey(this, "elementtype" + i));
            ingredientkey.add(new NamespacedKey(this, "ingredienttype" + i));
            ingredientsizekey.add(new NamespacedKey(this, "ingredientsize" + i));
            scrollsizekey.add(new NamespacedKey(this, "scrollsize" + i));
        }
    }

    public static void addSpell(Spell spell) {
        if(isupdating) {
            addingspells.add(spell);
        }else {
            updatingspells.add(spell);
        }
    }

    public void Update() {
        BukkitRunnable brun = new BukkitRunnable() {
            @Override
            public void run() {
                ArrayList<Spell> removingspells = new ArrayList<>();
                isupdating = true;
                for(Spell spell : updatingspells) {
                    if(!spell.isStart) {
                        spell.Start();
                        spell.ElementStart();
                        spell.isStart=true;
                    }
                    spell.Update();
                    spell.ElementUpdate();
                    if(spell.isEnd) {
                        spell.ElementEnd();
                        spell.runNext();
                        removingspells.add(spell);
                    }
                }
                isupdating = false;
                updatingspells.removeAll(removingspells);
                updatingspells.addAll(addingspells);
                addingspells.clear();
            }
        };brun.runTaskTimer(this, 0, 0);
    }

    public void onEnable() {
        keyLoad();
        Command cmd = new Command();
        Bukkit.getPluginCommand("magica").setExecutor(cmd);
        Bukkit.getPluginCommand("magica").setTabCompleter(cmd);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
        Bukkit.getPluginManager().registerEvents(new UseSkill(), this);
        Update();
    }

    public void onDisable() {
        for(Entity en : displayerentities) {
            en.remove();
        }
    }

}
