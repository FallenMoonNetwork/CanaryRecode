package net.canarymod.serialize;

import net.canarymod.Canary;
import net.canarymod.CanaryDeserializeException;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Enchantment;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.ItemStack;
import net.visualillusionsent.utils.StringUtils;

public class ItemSerializer implements Serializer<CanaryItem> {

    @Override
    public CanaryItem deserialize(String data) throws CanaryDeserializeException {
        String[] split = data.split("#");
        String[] item = split[0].split(";"); // The serialized item
        String[] enchantments = null;

        if (split.length >= 2) {
            enchantments = split[1].split(","); // CSV list of serialized enchantments
        }
        if (item.length < 4) {
            throw new CanaryDeserializeException("Could not deserialize Item. Expected fields 4. Found: " + item.length, getVendor());
        }
        CanaryItem citem = new CanaryItem(new ItemStack(parseInt(item[0]), parseInt(item[2]), parseInt(item[1])));
        if (item.length >= 5) {
            citem.setDisplayName(item[4]);
        }
        if (item.length >= 6) {
            citem.setLore(item[5].split(","));
        }

        citem.setSlot(parseInt(item[3]));
        if (enchantments != null) {
            EnchantmentSerializer es = new EnchantmentSerializer();

            for (String e : enchantments) {
                citem.addEnchantments(es.deserialize(e));
            }
        }
        return citem;
    }

    @Override
    public String serialize(CanaryItem obje) {
        CanaryItem obj = (CanaryItem) obje;
        StringBuilder fieldsItem = new StringBuilder();
        StringBuilder fieldsEnchants = new StringBuilder();

        // Process the item data:
        fieldsItem.append(obj.getId()).append(";").append(obj.getDamage()).append(";").append(obj.getAmount()).append(";").append(obj.getSlot());
        if (obj.hasLore()) {
            fieldsItem.append(";").append(StringUtils.joinString(obj.getLore(), ",", 0));
        }
        if (obj.hasDisplayName()) {
            fieldsItem.append(";").append(obj.getDisplayName());
        }
        // Process items enchantments
        Enchantment[] enchs = obj.getEnchantments();
        if (enchs != null) {
            for (Enchantment ench : obj.getEnchantments()) {
                fieldsEnchants.append(Canary.serialize(ench)).append(",");
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
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public String getType() {
        return Item.class.getSimpleName();
    }
}
