package taewookim.elements;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Transformation;
import taewookim.CustomMagica;
import taewookim.Elements;
import taewookim.magic.Spell;

public class Element_Block extends Element {

    ItemDisplay display;
    Transformation tran;
    int tick = 0;
    final int a;
    final double b;

    public Element_Block(Elements element, Integer strength) {
        super(element, strength);
        a = strength*2;
        b = ((double)strength)/2.0d;
    }

    @Override
    public void Start(Location loc) {
        display = (ItemDisplay) loc.getWorld().spawnEntity(loc, EntityType.ITEM_DISPLAY);
        tran = display.getTransformation();
        tran.getScale().set(0);
        display.setTransformation(tran);
        switch(element) {
            case FIRE:
                display.setItemStack(new ItemStack(Material.MAGMA_BLOCK));
                break;
            default:
                display.setItemStack(new ItemStack(Material.BLACK_CONCRETE));
                break;
        }
        CustomMagica.displayerentities.add(display);
    }

    @Override
    public void Update(Location loc) {
        loc.getWorld().spawnParticle(par, loc, a, b, b, b, 0);
        display.teleport(loc);
        if(tick<=10) {
            tick++;
            tran.getScale().set(((double)strength)*(((double)tick)/10.0d));
            display.setTransformation(tran);
        }
        for(Entity en : loc.getWorld().getNearbyEntities(BoundingBox.of(loc, strength, strength, strength))) {
            if(en instanceof LivingEntity le&&le.getNoDamageTicks()<=0) {
                spell.Damage(le, 2);
            }
        }
    }

    @Override
    public void End() {
        CustomMagica.displayerentities.remove(display);
        display.remove();
    }
}
