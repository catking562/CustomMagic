package taewookim.magic;

import org.bukkit.entity.LivingEntity;
import taewookim.elements.Element;

public class SpellBarrage extends Spell {

    public SpellBarrage(Element element, LivingEntity magica, Integer strength) {
        super(element, magica, strength);
    }

    @Override
    public void Update() {
        tick++;
        if(tick>=200) {

        }
    }
}
