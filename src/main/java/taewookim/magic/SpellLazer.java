package taewookim.magic;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import taewookim.elements.Element;

public class SpellLazer extends Spell {

    public SpellLazer(Element element, LivingEntity magica, Integer strength) {
        super(element, magica, strength);
    }

    @Override
    public void Start() {
        super.Start();
        if(vector==null) {
            vector = location.getDirection();
        }
        isEnd = true;
        Location loc = location.clone();
        for(int i = 0; i<strength*5; i++) {
            loc.add(vector);
            element.Update(loc);
            for(Entity en : loc.getWorld().getNearbyEntities(loc, 1, 1, 1)) {
                if(!en.equals(magica)&&en instanceof LivingEntity le) {
                    Damage(le, strength);
                }
            }
        }
        location = loc;
    }
}
