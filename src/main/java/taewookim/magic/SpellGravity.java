package taewookim.magic;

import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import taewookim.elements.Element;

public class SpellGravity extends Spell{

    public SpellGravity(Element element, LivingEntity magica, Integer strength) {
        super(element, magica, strength);
    }

    @Override
    public void Start() {
        super.Start();
        if(vector==null) {
            vector = location.getDirection().multiply(0.1);
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
            location.add(vector).add(0, -((double)tick)*0.01, 0);
            if(!location.getBlock().isPassable()) {
                isEnd = true;
            }
        }
    }
}
