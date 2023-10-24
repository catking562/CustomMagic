package taewookim.magic;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import taewookim.elements.Element;
import taewookim.math.R;

public class SpellUp extends Spell {

    public SpellUp(Element element, LivingEntity magica, Integer strength) {
        super(element, magica, strength);
    }

    @Override
    public void Start() {
        super.Start();
        isEnd = true;
        location.add(new Vector(0, R.random.nextDouble()*strength*2 + 15, 0));
        target = null;
    }

}
