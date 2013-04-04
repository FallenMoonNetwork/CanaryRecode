package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.canarymod.Canary;
import net.canarymod.api.potion.CanaryPotionEffect;
import net.canarymod.hook.player.EatHook;

public class ItemPotion extends Item {

    private HashMap a = new HashMap();
    private static final Map b = new LinkedHashMap();

    public ItemPotion(int i0) {
        super(i0);
        this.d(1);
        this.a(true);
        this.e(0);
        this.a(CreativeTabs.k);
    }

    public List g(ItemStack itemstack) {
        if (itemstack.p() && itemstack.q().b("CustomPotionEffects")) {
            ArrayList arraylist = new ArrayList();
            NBTTagList nbttaglist = itemstack.q().m("CustomPotionEffects");

            for (int i0 = 0; i0 < nbttaglist.c(); ++i0) {
                NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist.b(i0);

                arraylist.add(PotionEffect.b(nbttagcompound));
            }

            return arraylist;
        } else {
            List list = (List) this.a.get(Integer.valueOf(itemstack.k()));

            if (list == null) {
                list = PotionHelper.b(itemstack.k(), false);
                this.a.put(Integer.valueOf(itemstack.k()), list);
            }

            return list;
        }
    }

    public List c(int i0) {
        List list = (List) this.a.get(Integer.valueOf(i0));

        if (list == null) {
            list = PotionHelper.b(i0, false);
            this.a.put(Integer.valueOf(i0), list);
        }

        return list;
    }

    public ItemStack b(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        // CanaryMod: Eat
        net.canarymod.api.potion.PotionEffect[] effects = new net.canarymod.api.potion.PotionEffect[this.g(itemstack).size()];

        if (!world.I) {
            List list = this.g(itemstack);

            if (list != null) {
                Iterator iterator = list.iterator();

                int index = 0;
                while (iterator.hasNext()) {
                    PotionEffect potioneffect = (PotionEffect) iterator.next();

                    // entityplayer.d(new PotionEffect(potioneffect));
                    // add to the array first
                    effects[index] = new CanaryPotionEffect(potioneffect);
                    index++;
                    //
                }
            }
        }

        // Call Hook
        EatHook hook = new EatHook(((EntityPlayerMP) entityplayer).getPlayer(), itemstack.getCanaryItem(), 0, 0, effects);
        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            return itemstack;
        }
        // Apply food changes
        entityplayer.cl().a(hook.getLevelGain(), hook.getSaturationGain());
        // And finally add the effects
        if (hook.getPotionEffects() != null) {
            for (net.canarymod.api.potion.PotionEffect effect : hook.getPotionEffects()) {
                if (effect != null) {
                    entityplayer.d(((CanaryPotionEffect) effect).getHandle());
                }
            }
        }
        //

        if (!entityplayer.ce.d) { // moved
            --itemstack.a;
        }

        if (!entityplayer.ce.d) {
            if (itemstack.a <= 0) {
                return new ItemStack(Item.bu);
            }

            entityplayer.bK.a(new ItemStack(Item.bu));
        }

        return itemstack;
    }

    public int c_(ItemStack itemstack) {
        return 32;
    }

    public EnumAction b_(ItemStack itemstack) {
        return EnumAction.c;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (f(itemstack.k())) {
            if (!entityplayer.ce.d) {
                --itemstack.a;
            }

            world.a((Entity) entityplayer, "random.bow", 0.5F, 0.4F / (e.nextFloat() * 0.4F + 0.8F));
            if (!world.I) {
                world.d((Entity) (new EntityPotion(world, entityplayer, itemstack)));
            }

            return itemstack;
        } else {
            entityplayer.a(itemstack, this.c_(itemstack));
            return itemstack;
        }
    }

    public boolean a(ItemStack itemstack, EntityPlayer entityplayer, World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2) {
        return false;
    }

    public static boolean f(int i0) {
        return (i0 & 16384) != 0;
    }

    public String l(ItemStack itemstack) {
        if (itemstack.k() == 0) {
            return StatCollector.a("item.emptyPotion.name").trim();
        } else {
            String s0 = "";

            if (f(itemstack.k())) {
                s0 = StatCollector.a("potion.prefix.grenade").trim() + " ";
            }

            List list = Item.bt.g(itemstack);
            String s1;

            if (list != null && !list.isEmpty()) {
                s1 = ((PotionEffect) list.get(0)).f();
                s1 = s1 + ".postfix";
                return s0 + StatCollector.a(s1).trim();
            } else {
                s1 = PotionHelper.c(itemstack.k());
                return StatCollector.a(s1).trim() + " " + super.l(itemstack);
            }
        }
    }
}
