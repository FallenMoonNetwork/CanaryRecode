package net.canarymod.api.world;

import java.io.File;

import net.canarymod.Canary;

/**
 * MC Specific converter helper to move around vanilla world folder structure
 * to match canarymods world structure
 *
 * @author chris
 *
 */
public class CanarySaveConverter {
    /** The folder where all the world data is in */
    private File baseFolder;

    private File endWorld;
    private File netherWorld;
    private File normalWorld;

    public CanarySaveConverter(File baseFolder) {
        this.baseFolder = baseFolder;
    }

    /**
     * Check if the world for this SaveConverter is in vanilla format.
     * This will also prepare folders for movement if required.
     *
     * @return
     */
    public boolean isVanillaFormat() {
        boolean isVanilla = false;
        for(File file : baseFolder.listFiles()) {
            if(file.isDirectory()) {
                if(file.getName().equals("DIM1")) {
                    endWorld = file;
                    isVanilla = true;
                }
                if(file.getName().equals("DIM-1")) {
                    netherWorld = file;
                    isVanilla = true;
                }
            }
        }
        if(isVanilla) {
            normalWorld = new File(baseFolder, baseFolder.getName() + "_" + "NORMAL");
            normalWorld.mkdirs();
        }
        return isVanilla;
    }

    /**
     * Executes the conversion from vanilla to Canary World Format
     */
    public void convert() {
        //Check for the DIM folders
        if(netherWorld != null) {
            if(netherWorld.renameTo(new File(baseFolder, baseFolder.getName() + "_" + "NETHER"))) {
                Canary.logInfo("Nether converted successfully!");
            }
            else {
                Canary.logWarning("Failed to convert the Nether!");
            }
        }
        else {
            Canary.logInfo("No Nether World to convert.");
        }

        if(endWorld != null) {
            if(endWorld.renameTo(new File(baseFolder, baseFolder.getName() + "_" + "END"))) {
                Canary.logInfo("The End converted successfully!");
            }
            else {
                Canary.logWarning("Failed to convert the End!");
            }
        }
        else {
            Canary.logInfo("No End World to convert.");
        }
        //Now move region and data into normalWorld
        for(File f : baseFolder.listFiles()) {
            if(f.getName().equals("region")) {
                f.renameTo(new File(normalWorld, "region"));
            }
            if(f.getName().equals("data")) {
                f.renameTo(new File(normalWorld, "data"));
            }
            if(f.getName().equals("level.dat")) {
                f.renameTo(new File(normalWorld, "level.dat"));
            }
        }

        Canary.logInfo("If you see no Warnings above, conversion was successful!");
    }
}
