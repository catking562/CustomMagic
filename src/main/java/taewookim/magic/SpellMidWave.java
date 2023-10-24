package taewookim.magic;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import taewookim.CustomMagica;
import taewookim.elements.Element;
import taewookim.math.TriangleMath;

import java.util.ArrayList;

public class SpellMidWave extends Spell{

    public SpellMidWave(Element element, LivingEntity magica, Integer strength) {
        super(element, magica, strength);
    }

    @Override
    public void Start() {
        super.Start();
        isEnd = true;
        if(canClone) {
            switch(element.getClass().getSimpleName()) {
                case "Element":
                    for(int i = 0;i<60;i++) {
                        Spell spell = new SpellPassProjectile(this.element.clone(), magica, strength);
                        spell.canClone = false;
                        Location loc = this.location.clone();
                        double dx = TriangleMath.cos.get(i*6);
                        double dy = TriangleMath.sin.get(i*6);
                        Vector v = new Vector(dx/((double)(strength*strength)), 0, dy/((double)(strength*strength)));
                        loc.setDirection(v.clone());
                        spell.location = loc;
                        spell.vector = v;
                        spell.damage = this.damage;
                        CustomMagica.addSpell(spell);
                    }
                    break;
                default:
                    for(int i = 0;i<10;i++) {
                        Spell spell = new SpellPassProjectile(this.element.clone(), magica, strength);
                        spell.canClone = false;
                        Location loc = this.location.clone();
                        double dx = TriangleMath.cos.get(i*36);
                        double dy = TriangleMath.sin.get(i*36);
                        Vector v = new Vector(dx/((double)(strength*strength)), 0, dy/((double)(strength*strength)));
                        loc.setDirection(v.clone());
                        spell.location = loc;
                        spell.vector = v;
                        spell.damage = this.damage;
                        CustomMagica.addSpell(spell);
                    }
                    break;
            }
        }
    }
}
