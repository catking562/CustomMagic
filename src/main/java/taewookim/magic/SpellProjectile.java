package taewookim.magic;

import org.bukkit.entity.LivingEntity;
import taewookim.elements.Element;

public class SpellProjectile extends Spell {
    public SpellProjectile(Element element, LivingEntity magica, Integer strength) {
        super(element, magica, strength);
    }

    @Override
    public void Start() {
        super.Start();
        if(vector==null) {
            vector = location.getDirection().multiply(0.5);
        }
    }

    @Override
    public void Damage(LivingEntity en, double damage) {
        if(!canDamage(en)) {return;}
        super.Damage(en, damage);
        isEnd = true;
    }

    @Override
    public void Update() {
        super.Update();
        if(tick>=10) {
            location.add(vector);
            if(!location.getBlock().isPassable()) {
                isEnd = true;
            }
        }
    }
}
