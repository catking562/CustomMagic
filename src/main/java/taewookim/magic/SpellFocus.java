package taewookim.magic;

import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import taewookim.elements.Element;
import taewookim.math.R;

import java.util.ArrayList;

public class SpellFocus extends Spell {

    public SpellFocus(Element element, LivingEntity magica, Integer strength) {
        super(element, magica, strength);
    }

    @Override
    public void Start() {
        super.Start();
        if(canClone) {
            Spell spell = nextspells.get(0);
            nextspells.remove(0);
            for(int i = 0;i<strength*2;i++) {
                Spell spell1 = spell.clone();
                spell1.location = this.location.add((R.random.nextDouble()-0.5)*((double)strength)*4.0, R.random.nextDouble()*((double)strength)*2.0, (R.random.nextDouble()-0.5)*((double)strength)*4.0);
                spell1.vector = new Vector(R.random.nextDouble() - 0.5, R.random.nextDouble() - 0.5, R.random.nextDouble() - 0.5);
                spell1.target = this.target;
                spell1.damage = this.damage;
                spell1.canClone = false;
                ArrayList<Spell> list = new ArrayList<>();
                for(Spell s : nextspells) {
                    list.add(s);
                }
                spell1.nextspells = list;
            }
        }
    }
}
