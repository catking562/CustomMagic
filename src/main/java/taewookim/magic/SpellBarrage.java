package taewookim.magic;

import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import taewookim.CustomMagica;
import taewookim.elements.Element;
import taewookim.math.R;

import java.util.ArrayList;

public class SpellBarrage extends Spell {

    int a = 0;
    final int b;
    Spell next;

    public SpellBarrage(Element element, LivingEntity magica, Integer strength) {
        super(element, magica, strength);
        b = 40-strength*7;
    }

    @Override
    public void Start() {
        super.Start();
        if(!canClone||nextspells==null||nextspells.size()==0) {
            isEnd = true;
        }else {
            next = nextspells.get(0);
            nextspells.remove(0);
        }
        target = null;
    }

    @Override
    public void runNext() {

    }

    @Override
    public void Update() {
        tick++;
        if(tick>=200) {
            isEnd = true;
        }
        a++;
        if(a>=b) {
            a = 0;
            Spell spell = next.clone();
            ArrayList<Spell> list = new ArrayList<>();
            for(Spell s : nextspells) {
                list.add(s.clone());
            }
            spell.nextspells = list;
            spell.location = this.location.clone();
            spell.vector = new Vector(R.random.nextDouble() - 0.5, R.random.nextDouble() - 0.5, R.random.nextDouble() - 0.5);
            spell.target = this.target;
            spell.damage = this.damage;
            spell.canClone = false;
            CustomMagica.addSpell(spell);
        }
    }
}
