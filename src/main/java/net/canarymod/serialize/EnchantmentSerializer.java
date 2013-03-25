package net.canarymod.serialize;


import net.canarymod.CanaryDeserializeException;
import net.canarymod.api.CanaryEnchantment;
import net.canarymod.api.Enchantment;


public class EnchantmentSerializer implements Serializer<CanaryEnchantment> {

    @Override
    public CanaryEnchantment deserialize(String data) throws CanaryDeserializeException {
        try {
            String[] fields = data.split(";");

            if (fields.length != 2) {
                throw new CanaryDeserializeException("Could not deserialize Enchantment: Number of fields do not match expected fields (" + fields.length + "/2)", getVendor());
            }
            Enchantment.Type type = Enchantment.Type.fromId(Integer.parseInt(fields[0]));
            CanaryEnchantment ench = new CanaryEnchantment(type, Integer.parseInt(fields[1]));

            return ench;
        } catch (NumberFormatException e) {
            throw new CanaryDeserializeException("Could not deserialize Enchantment: " + e.getMessage(), getVendor());
        }
    }

    @Override
    public String serialize(CanaryEnchantment obje) {
        if (!(obje instanceof CanaryEnchantment)) {
            return null;
        }
        CanaryEnchantment obj = (CanaryEnchantment) obje;
        StringBuilder fields = new StringBuilder();

        fields.append(obj.getType().getId()).append(";").append(obj.getLevel());
        return fields.toString();
    }

    @Override
    public String getVendor() {
        return "CanaryMod";
    }

    @Override
    public String getType() {
        return Enchantment.class.getSimpleName();
    }

}
