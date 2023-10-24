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
        isEnd = true;
        Location loc = location.clone();
        element.Update(loc);
        for(int i = 0; i<strength*5; i++) {
            loc.add(loc.getDirection());
            for(Entity en : loc.getWorld().getNearbyEntities(loc, 1, 1, 1)) {
                if(!en.equals(magica)&&en instanceof LivingEntity le) {
                    Damage(le, strength);
                }
            }
        }
        location = loc;
    }
}
