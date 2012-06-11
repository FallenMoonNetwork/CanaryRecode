package net.canarymod.serialize;

import net.canarymod.CanaryDeserializeException;
import net.canarymod.api.CanaryEnchantment;
import net.canarymod.api.Enchantment.Type;
import net.canarymod.api.inventory.CanaryItem;
import net.minecraft.server.OItemStack;

public class ItemSerializer implements Serializer<CanaryItem> {

    @Override
    public CanaryItem deserialize(String data) throws CanaryDeserializeException {
        int id, meta, amount, slot;
        CanaryEnchantment enchantment;
        String[] split = data.replaceAll("[\\[\\]]","").split(",");
        if(split.length < 5) {
            throw new CanaryDeserializeException("Could not deserialize Item. Invalid data.");
        }
        id = parseInt(split[0]);
        meta = parseInt(split[1]);
        amount = parseInt(split[2]);
        slot = parseInt(split[3]);
        
        String[] ench = split[4].split(":");
        
        enchantment = new CanaryEnchantment(Type.fromId(Integer.valueOf(ench[0])), Integer.valueOf(ench[1]));
        CanaryItem item = new CanaryItem(new OItemStack(id, amount, meta));
        item.addEnchantment(enchantment);
        item.setSlot(slot);
        return item;
    }

    @Override
    public String serialize(CanaryItem object) {
        // TODO Auto-generated method stub
        return null;
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
}
