package taewookim.elements;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BoundingBox;
import taewookim.ElementType;
import taewookim.Elements;
import taewookim.magic.Spell;

public class Element {

    public final Elements element;
    public final Particle par;
    public final Color color;
    public final int strength;
    public Spell spell;

    public Element(Elements element, Integer strength) {
        this.element = element;
        switch(element) {
            case FIRE:
                par = Particle.FLAME;
                color = Color.RED;
                break;
            case WATER:
                par = Particle.WATER_DROP;
                color = Color.AQUA;
                break;
            default:
                par = Particle.CLOUD;
                color = Color.BLACK;
                break;
        }
        this.strength = strength;
    }

    public Element clone() {
        try{
            Element cl = this.getClass().getDeclaredConstructor(new Class[]{Elements.class, Integer.class}).newInstance(element, strength);
            return cl;
        }catch(Exception e) {
            return null;
        }
    }

    public void Start(Location loc) {

    }

    public void Update(Location loc) {
        loc.getWorld().spawnParticle(par, loc, strength, 0.1, 0.1, 0.1, 0.01);
        for(Entity en : loc.getWorld().getNearbyEntities(BoundingBox.of(loc, 1, 1, 1))) {
            if(en instanceof LivingEntity le&&le.getNoDamageTicks()<=0) {
                spell.Damage(le, 1);
            }
        }
    }

    public void End() {

    }

}
