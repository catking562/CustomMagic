package taewookim.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import taewookim.CustomMagica;
import taewookim.ElementType;
import taewookim.Elements;
import taewookim.Spells;
import taewookim.elements.Element;
import taewookim.magic.Spell;

import java.util.ArrayList;

public class UseSkill implements Listener {

    public static void useSkill(Player p, PersistentDataContainer per) {
        ArrayList<Spell> list = new ArrayList<>();
        for(int i = 1; i<per.get(CustomMagica.skillsizekey, PersistentDataType.INTEGER);i++) {
            try{
                Element element = ElementType.valueOf(per.get(CustomMagica.ingredientkey.get(i), PersistentDataType.STRING)).getClz().getDeclaredConstructor(new Class[]{Elements.class, Integer.class}).newInstance(Elements.valueOf(per.get(CustomMagica.elementkey.get(i), PersistentDataType.STRING)), per.get(CustomMagica.ingredientsizekey.get(i), PersistentDataType.INTEGER));
                Spell spell1 = Spells.valueOf(per.get(CustomMagica.scrollkey.get(i), PersistentDataType.STRING)).getClz().getDeclaredConstructor(new Class[]{Element.class, LivingEntity.class, Integer.class}).newInstance(element, p, per.get(CustomMagica.scrollsizekey.get(i), PersistentDataType.INTEGER));
                list.add(spell1);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        try{
            Element e = ElementType.valueOf(per.get(CustomMagica.ingredientkey.get(0), PersistentDataType.STRING)).getClz().getDeclaredConstructor(new Class[]{Elements.class, Integer.class}).newInstance(Elements.valueOf(per.get(CustomMagica.elementkey.get(0), PersistentDataType.STRING)), per.get(CustomMagica.ingredientsizekey.get(0), PersistentDataType.INTEGER));
            Spell spell = Spells.valueOf(per.get(CustomMagica.scrollkey.get(0), PersistentDataType.STRING)).getClz().getDeclaredConstructor(new Class[]{Element.class, LivingEntity.class, Integer.class}).newInstance(e, p, per.get(CustomMagica.scrollsizekey.get(0), PersistentDataType.INTEGER));
            spell.nextspells = list;
            CustomMagica.addSpell(spell);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void inter(PlayerInteractEvent e) {
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if(e.getPlayer().getItemInHand()!=null) {
                ItemStack i = e.getPlayer().getItemInHand();
                if(i.hasItemMeta()&&!e.getPlayer().hasCooldown(i.getType())) {
                    ItemMeta m = i.getItemMeta();
                    PersistentDataContainer per = m.getPersistentDataContainer();
                    if(per.has(CustomMagica.itemtypekey, PersistentDataType.STRING)
                            &&per.get(CustomMagica.itemtypekey, PersistentDataType.STRING).equalsIgnoreCase("magicbook")) {
                        useSkill(e.getPlayer(), per);
                    }
                }
            }
        }
    }

}
