package net.canarymod.serialize;

import net.canarymod.CanaryDeserializeException;
import net.canarymod.api.CanaryEnchantment;
import net.canarymod.api.Enchantment;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.OItemStack;

public class ItemSerializer implements Serializer<CanaryItem> {

    @Override
    public CanaryItem deserialize(String data) throws CanaryDeserializeException {
        int id, meta, amount, slot;
        
        String[] split = data.split("#");
        String[] item = split[0].split(";"); //The serialized item
        String[] enchantments = null;
        if(split.length == 2) {
            enchantments = split[1].split(","); //CSV list of serialized enchantments
        }
        if(item.length < 4) {
            throw new CanaryDeserializeException("Could not deserialize Item. Expected fields 4. Found: "+item.length, getVendor());
        }
        id = parseInt(split[0]);
        meta = parseInt(split[1]);
        amount = parseInt(split[2]);
        slot = parseInt(split[3]);
        CanaryItem citem = new CanaryItem(new OItemStack(id, amount, meta));
        citem.setSlot(slot);
        if(enchantments != null) {
            EnchantmentSerializer es = new EnchantmentSerializer();
            for(String e : enchantments) {
                citem.addEnchantment(es.deserialize(e));
            }
        }
        return citem;
    }

    @Override
    public String serialize(Object obje) {
        if(!(obje instanceof CanaryItem)) {
            return null;
        }
        CanaryItem obj = (CanaryItem) obje;
        StringBuilder fieldsItem = new StringBuilder();
        StringBuilder fieldsEnchants = new StringBuilder();
        //Process the item data:
        fieldsItem.append(obj.getId()).append(";")
            .append(obj.getDamage()).append(";")
            .append(obj.getAmount()).append(";")
            .append(obj.getSlot());
        //Process items enchantments
        EnchantmentSerializer es = new EnchantmentSerializer();
        Enchantment[] enchs = obj.getEnchantments();
        if(enchs != null) {
            for(Enchantment ench : obj.getEnchantments()) {
                fieldsEnchants.append(es.serialize((CanaryEnchantment) ench)).append(",");
            }
            fieldsItem.append("#").append(fieldsEnchants);
        }
        return fieldsItem.toString();
    }

    @Override
    public String getVendor() {
        return "CanaryMod";
    }
    
    private int parseInt(String s) {
        int ret = -1;
        try {
            ret = Integer.parseInt(s);
        }
        catch(NumberFormatException e) {
            ret = -1;
        }
        return ret;
    }

    @Override
    public String getType() {
        return Item.class.getSimpleName();
    }
}
