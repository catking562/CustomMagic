package taewookim.magic;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import taewookim.CustomMagica;
import taewookim.elements.Element;
import taewookim.math.TriangleMath;

import java.util.ArrayList;

public class SpellWave extends Spell{

    public SpellWave(Element element, LivingEntity magica, Integer strength) {
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
                        ArrayList<Spell> nextlist = new ArrayList<>();
                        for(Spell cs : this.nextspells) {
                            nextlist.add(cs.clone());
                        }
                        spell.nextspells = nextlist;
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
                        ArrayList<Spell> nextlist = new ArrayList<>();
                        for(Spell cs : this.nextspells) {
                            nextlist.add(cs.clone());
                        }
                        spell.nextspells = nextlist;
                        CustomMagica.addSpell(spell);
                    }
                    break;
            }
        }
    }

    @Override
    public void runNext() {
    }
}
