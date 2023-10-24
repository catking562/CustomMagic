package taewookim.magic;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BoundingBox;
import taewookim.elements.Element;

public class SpellExplosion extends Spell {

    public SpellExplosion(Element element, LivingEntity magica, Integer strength) {
        super(element, magica, strength);
    }

    @Override
    public void Start() {
        super.Start();
    }

    @Override
    public void Update() {
        isEnd = true;
        location.getWorld().spawnParticle(strength>=3?Particle.EXPLOSION_HUGE:Particle.EXPLOSION_LARGE, location, 1, 0, 0, 0, 0);
        location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
        for(Entity en : location.getWorld().getNearbyEntities(BoundingBox.of(location, strength, strength, strength))) {
            if(en instanceof LivingEntity le) {
                Damage(le, strength);
            }
        }
    }
}
