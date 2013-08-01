package net.canarymod.api.inventory.recipes;

import java.util.ArrayList;
import net.canarymod.api.inventory.CanaryItem;

/**
 * Shaped Recipe Helper
 * <p>
 * Converts a Shaped Recipe into the Minecraft format
 * 
 * @author Jason (darkdiplomat)
 */
public final class ShapedRecipeHelper {

    public static Object[] createRecipeShape(CraftingRecipe recipe) {
        if (recipe.hasShape()) {
            ArrayList<Object> rawRecipe = new ArrayList<Object>();
            RecipeRow[] rows = recipe.getRows();
            RecipeRow a = rows[0];
            RecipeRow b = null;
            RecipeRow c = null;

            if (rows.length > 1) {
                b = rows[1];
            }
            if (rows.length > 2) {
                c = rows[2];
            }
            rawRecipe.add(a.getShape());
            if (b != null) {
                rawRecipe.add(b.getShape());
            }
            if (c != null) {
                rawRecipe.add(c.getShape());
            }
            Character lastid = null;
            int index = 0;

            for (char id : a.getIdentifiers()) {
                if (lastid != null && id == lastid.charValue() || id == ' ') {
                    continue;
                }
                lastid = new Character(id);
                rawRecipe.add(lastid);
                rawRecipe.add(((CanaryItem) a.getItems()[index]).getHandle());
                index++;
            }
            index = 0; // reset index
            if (b != null) {
                for (char id : b.getIdentifiers()) {
                    if (lastid != null && id == lastid.charValue() || id == ' ') { // filter matching chars
                        if (id != ' ') {
                            index++; // Item index needs moved too
                        }
                        continue;
                    }
                    lastid = new Character(id);
                    if (rawRecipe.contains(lastid)) { // filter matching chars from previous rows
                        index++; // Item index needs moved too
                        continue;
                    }
                    rawRecipe.add(lastid);
                    rawRecipe.add(((CanaryItem) b.getItems()[index]).getHandle());
                    index++;
                }
            }
            index = 0; // reset index
            if (c != null) {
                for (char id : c.getIdentifiers()) {
                    if (lastid != null && id == lastid.charValue() || id == ' ') { // filter matching chars
                        if (id != ' ') {
                            index++; // Item index needs moved too
                        }
                        continue;
                    }
                    lastid = new Character(id);
                    if (rawRecipe.contains(lastid)) { // filter matching chars from previous rows
                        index++; // Item index needs moved too
                        continue;
                    }
                    rawRecipe.add(lastid);
                    rawRecipe.add(((CanaryItem) c.getItems()[index]).getHandle());
                    index++;
                }
            }

            return rawRecipe.toArray(new Object[rawRecipe.size()]);
        }
        return null;
    }
}
