package taewookim.magic;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import taewookim.elements.Element;
import taewookim.math.R;

import java.util.ArrayList;

public class SpellNearTarget extends Spell {

    public SpellNearTarget(Element element, LivingEntity magica, Integer strength) {
        super(element, magica, strength);
    }

    @Override
    public void Start() {
        super.Start();
        isEnd = true;
        ArrayList<LivingEntity> list = new ArrayList<>();
        for(Entity en : location.getWorld().getNearbyEntities(BoundingBox.of(location, strength*5, strength*5, strength*5))) {
            if(!en.equals(magica)&&en instanceof LivingEntity le) {
                list.add(le);
            }
        }
        if(list.size()==0) {
            vector = new Vector(R.random.nextDouble()-0.5, R.random.nextDouble()-0.5, R.random.nextDouble()-0.5);
        }else {
            Location toloc = list.get(R.random.nextInt(list.size())).getLocation();
            Vector v = toloc.add(location.clone().multiply(-1)).toVector();
            vector = v.multiply(1.0/Math.sqrt(v.getX()*v.getX()+v.getY()*v.getY()+v.getZ()*v.getZ()));
        }
    }
}
