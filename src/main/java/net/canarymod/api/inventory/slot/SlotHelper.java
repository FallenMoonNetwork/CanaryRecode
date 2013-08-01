package net.canarymod.api.inventory.slot;

import net.canarymod.api.inventory.Inventory;
import net.minecraft.server.Container;
import net.minecraft.server.ContainerBeacon;
import net.minecraft.server.ContainerBrewingStand;
import net.minecraft.server.ContainerChest;
import net.minecraft.server.ContainerDispenser;
import net.minecraft.server.ContainerEnchantment;
import net.minecraft.server.ContainerFurnace;
import net.minecraft.server.ContainerHorseInventory;
import net.minecraft.server.ContainerHorseInventorySlotArmor;
import net.minecraft.server.ContainerHorseInventorySlotSaddle;
import net.minecraft.server.ContainerMerchant;
import net.minecraft.server.ContainerPlayer;
import net.minecraft.server.ContainerRepair;
import net.minecraft.server.ContainerRepairINNER2;
import net.minecraft.server.ContainerWorkbench;
import net.minecraft.server.Slot;
import net.minecraft.server.SlotArmor;
import net.minecraft.server.SlotBeacon;
import net.minecraft.server.SlotBrewingStandIngredient;
import net.minecraft.server.SlotBrewingStandPotion;
import net.minecraft.server.SlotCrafting;
import net.minecraft.server.SlotEnchantment;
import net.minecraft.server.SlotFurnace;
import net.minecraft.server.SlotMerchantResult;

/**
 * SlotHelper for SlotClickHook
 * 
 * @author Jason (darkdiplomat)
 */
public final class SlotHelper {

    /**
     * Matches a Container and slot index to a {@link SlotType}
     * 
     * @param container
     *            the Container being used
     * @param slotIndex
     *            the slot id
     * @return the {@link SlotType}
     */
    public static SlotType getSlotType(Container container, int slotIndex) {
        if (container == null) {
            return SlotType.NULL;
        }

        // Outside window
        if (slotIndex == -999) {
            return SlotType.OUTSIDE;
        }

        // In Window/non-slot
        if (slotIndex == -1) {
            return SlotType.NULL;
        }

        Slot slot = container.a(slotIndex);
        if (slot instanceof SlotArmor) {
            return SlotType.ARMOR;
        } else if (slot instanceof SlotBeacon) {
            return SlotType.BEACON;
        } else if (slot instanceof SlotBrewingStandIngredient) {
            return SlotType.INGREDIENT;
        } else if (slot instanceof SlotBrewingStandPotion) {
            return SlotType.POTION;
        } else if (slot instanceof SlotCrafting) {
            return SlotType.CRAFTING;
        } else if (slot instanceof SlotEnchantment) {
            return SlotType.ENCHANTMENT;
        } else if (slot instanceof SlotFurnace) {
            return SlotType.FURNACE;
        } else if (slot instanceof SlotMerchantResult) {
            return SlotType.MERCHANT;
        } else if (slot instanceof ContainerRepairINNER2) {
            return SlotType.REPAIR;
        } else if (slot instanceof ContainerHorseInventorySlotSaddle) {
            return SlotType.SADDLE;
        } else if (slot instanceof ContainerHorseInventorySlotArmor) {
            return SlotType.ARMOR;
        } else if (slot.getClass() != Slot.class) {
            return SlotType.UNKNOWN;
        } else {
            return SlotType.DEFAULT;
        }
    }

    /**
     * Attempts to define slots. Minecraft updates can break the definitions if slots are added/removed/modified.
     * 
     * @return {@link SecondarySlotType}
     */
    public static SecondarySlotType getSpecificSlotType(Container container, int slotIndex) {
        if (container == null) {
            return SecondarySlotType.NULL;
        }

        // In Window, non-slot
        if (slotIndex == -1) {
            return SecondarySlotType.NULL;
        }

        // Outside window
        if (slotIndex == -999) {
            return SecondarySlotType.OUTSIDE;
        }

        Inventory inv = container.getInventory();
        if (inv == null) {
            return SecondarySlotType.NULL;
        }

        if (slotIndex < inv.getContents().length) {
            if (container instanceof ContainerBeacon) {
                return SecondarySlotType.PAYMENT;
            } else if (container instanceof ContainerBrewingStand) {
                switch (slotIndex) {
                    case 3:
                        return SecondarySlotType.CRAFT;
                    default:
                        return SecondarySlotType.POTION;
                }
            } else if (container instanceof ContainerChest) {
                return SecondarySlotType.CONTAINER;
            } else if (container instanceof ContainerDispenser) {
                return SecondarySlotType.CONTAINER;
            } else if (container instanceof ContainerEnchantment) {
                return SecondarySlotType.ENCHANT;
            } else if (container instanceof ContainerFurnace) {
                switch (slotIndex) {
                    case 0:
                        return SecondarySlotType.CRAFT;
                    case 1:
                        return SecondarySlotType.FUEL;
                    default:
                        return SecondarySlotType.RESULT;
                }
            } else if (container instanceof ContainerMerchant) {
                switch (slotIndex) {
                    case 0:
                    case 1:
                        return SecondarySlotType.TRADE;
                    default:
                        return SecondarySlotType.RESULT;
                }
            } else if (container instanceof ContainerPlayer) {
                if (slotIndex == 0) {
                    return SecondarySlotType.RESULT;
                }
                if (slotIndex <= 4) {
                    return SecondarySlotType.CRAFT;
                }
            } else if (container instanceof ContainerRepair) {
                switch (slotIndex) {
                    case 0:
                    case 1:
                        return SecondarySlotType.CRAFT;
                    default:
                        return SecondarySlotType.RESULT;
                }
            } else if (container instanceof ContainerWorkbench) {
                if (slotIndex == 0) {
                    return SecondarySlotType.RESULT;
                }
                if (slotIndex <= 9) {
                    return SecondarySlotType.CRAFT;
                }
            } else if (container instanceof ContainerHorseInventory) {
                if (slotIndex == 0) {
                    return SecondarySlotType.SADDLE;
                }
                if (slotIndex == 1) {
                    return SecondarySlotType.HORSE_ARMOR;
                }
            }
        }

        int localSlot = slotIndex - inv.getContents().length;

        if (container instanceof ContainerPlayer) {
            if (localSlot < 4) {
                return SecondarySlotType.ARMOR;
            }

            localSlot -= 4; // remove armor index
        }

        if (localSlot >= 27 && localSlot < 36) {
            return SecondarySlotType.QUICKBAR;
        }

        return SecondarySlotType.INVENTORY;
    }
}
