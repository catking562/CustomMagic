package taewookim.magic;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import taewookim.elements.Element;

public class SpellTarget extends Spell {

    public SpellTarget(Element element, LivingEntity magica, Integer strength) {
        super(element, magica, strength);
    }

    @Override
    public void Start() {
        super.Start();
        isEnd = true;
        Location loc = location.clone();
        for(int i = 0; i<strength*5; i++) {
            loc.add(loc.getDirection());
            for(Entity en : loc.getWorld().getNearbyEntities(loc, 1, 1, 1)) {
                if(!en.equals(magica)&&en instanceof LivingEntity le) {
                    target = le;
                    Damage(le, strength);
                }
            }
            if(!loc.getBlock().isPassable()) {
                break;
            }
        }
        location = loc;
    }
}
