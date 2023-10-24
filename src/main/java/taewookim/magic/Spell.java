package taewookim.magic;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import taewookim.CustomMagica;
import taewookim.Elements;
import taewookim.elements.Element;

import java.util.ArrayList;

public class Spell {

    public ArrayList<Spell> nextspells;
    public LivingEntity target = null;
    public Location location = null;
    public Vector vector = null;
    public double damage = 1;
    public boolean canClone = true;

    public final int strength;
    public final LivingEntity magica;
    public final Element element;

    public boolean isEnd = false;
    public boolean isStart = false;
    public int tick = 0;
    public final int maxtick;

    public Spell(Element element, LivingEntity magica, Integer strength) {
        this.element = element;
        this.strength = strength;
        this.magica = magica;
        element.spell = this;
        maxtick = 10*strength*strength;
    }

    public void runNext() {
        if(nextspells!=null&&nextspells.size()>0) {
            Spell spell = nextspells.get(0);
            nextspells.remove(0);
            spell.location = this.location;
            spell.vector = this.vector;
            spell.target = this.target;
            spell.damage = this.damage;
            spell.canClone = this.canClone;
            spell.nextspells = (ArrayList<Spell>) this.nextspells.clone();
            CustomMagica.addSpell(spell);
        }
    }

    public boolean canDamage(LivingEntity en) {
        return !en.equals(magica);
    }

    public void Damage_None(LivingEntity en, double damage) {
        en.damage(damage, magica);
    }

    public void Damage_Fire(LivingEntity en, double damage) {
        en.damage(damage, magica);
        en.setFireTicks(20*strength);
    }

    public void Damage(LivingEntity en, double damage) {
        if(!canDamage(en)) {return;}
        Elements ele = element.element;
        switch(ele) {
            case NONE:
                Damage_None(en, damage*this.damage);
                break;
            case FIRE:
                Damage_Fire(en, damage*this.damage);
                break;
        }
    }

    public void ElementStart() {
        if(target!=null) {
            element.Start(target.getLocation());
        }else if(location!=null) {
            element.Start(location);
        }else {
            element.Start(magica.getLocation());
        }
    }

    public void ElementUpdate() {
        if(target!=null) {
            element.Update(target.getLocation());
        }else if(location!=null) {
            element.Update(location);
        }else {
            element.Update(magica.getLocation());
        }
    }

    public void ElementEnd() {
        element.End();
    }

    public void Start() {
        if(target!=null&&!target.isDead()) {
            location = target.getLocation();
        }
        if(location==null) {
            location = magica.getLocation().add(0, 1.75, 0);
            location.add(location.getDirection());
        }
    }

    public void Update() {
        tick++;
        if(tick>=maxtick) {
            isEnd = true;
        }
    }

    public Spell clone() {
        try{
            Spell spell = this.getClass().getDeclaredConstructor(new Class[]{Element.class, LivingEntity.class, Integer.class}).newInstance(element.clone(), magica, strength);
            spell.location = this.location;
            spell.vector = this.vector;
            spell.target = this.target;
            spell.damage = this.damage;
            spell.canClone = this.canClone;
            if(nextspells!=null) {
                ArrayList<Spell> nextlist = new ArrayList();
                for(Spell ns : (ArrayList<Spell>) nextspells.clone()) {
                    nextlist.add(ns.clone());
                }
                spell.nextspells = nextlist;
            }else {
                spell.nextspells = new ArrayList<>();
            }
            return spell;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
