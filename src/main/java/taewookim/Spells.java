package taewookim;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import taewookim.magic.*;

import java.util.Arrays;

public enum Spells {

    NONE("없음", Spell.class), PROJECTILE("발사체", SpellProjectile.class), EXPLOSION("폭발", SpellExplosion.class),
    WAVE("웨이브", SpellWave.class), TARGET("타게팅", SpellTarget.class), UP("업", SpellUp.class), GRAVITY("그래비티", SpellGravity.class),
    MIDWAVE("미드웨이브", SpellMidWave.class), LAZER("레이저", SpellLazer.class)
    ;

    final String name;
    final Class<? extends Spell> clz;

    Spells(String name, Class<? extends Spell> clz) {
        this.name = name;
        this.clz = clz;
    }

    public ItemStack getItem() {
        ItemStack i = new ItemStack(Material.PAPER);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName("§c마법 스크롤");
        m.setLore(Arrays.asList("§7"+name));
        PersistentDataContainer container = m.getPersistentDataContainer();
        container.set(CustomMagica.scrollkey.get(0), PersistentDataType.STRING, this.toString());
        container.set(CustomMagica.itemtypekey, PersistentDataType.STRING, "scroll");
        i.setItemMeta(m);
        return i;
    }

    public Class<? extends Spell> getClz() {
        return clz;
    }

}
