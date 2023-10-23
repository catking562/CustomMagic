package taewookim.elements;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BoundingBox;
import taewookim.Elements;
import taewookim.magic.Spell;

public class Element_Normal extends Element {

    final int a;

    public Element_Normal(Elements element, Integer strength) {
        super(element, strength);
        a = 10*strength;
    }

    @Override
    public void Update(Location loc) {
        loc.getWorld().spawnParticle(par, loc, a, 0.3, 0.3, 0.3, 0.1);
        loc.getWorld().spawnParticle(Particle.REDSTONE, loc, a, 0.3, 0.3, 0.3, 0.1, new Particle.DustOptions(color, 1));
        for(Entity en : loc.getWorld().getNearbyEntities(BoundingBox.of(loc, 1.5, 1.5, 1.5))) {
            if(en instanceof LivingEntity le&&le.getNoDamageTicks()<=0) {
                spell.Damage(le, 1);
            }
        }
    }
}
