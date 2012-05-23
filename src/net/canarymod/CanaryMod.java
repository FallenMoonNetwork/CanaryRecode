package net.canarymod;

//import java.util.logging.Logger;

import net.canarymod.backbone.Backbone;
import net.canarymod.hook.Hook;
import net.canarymod.hook.HookExecutor;
import net.canarymod.hook.player.ChatDelegate;
import net.canarymod.plugin.PluginLoader;

/**
 * The implementation of ICanary, a singleton used for everything.
 * 
 * @author Chris Ksoll
 * @author Jos Kuijpers
 */
public class CanaryMod extends Canary {

//    private static final Logger log = Logger.getLogger("Minecraft");
    
    /**
     * Private Canary Constructor to prevent more than once instance.<br>
     * <b>NOTE: </b>This can only be instanciated ONCE. It is a form of singleton.
     * In-Code other than where it is originally initialized, use ICanary.get()
     * to get your Canary instance!
     * TODO: add subsystem inits
     * and the respective backbones
     */
    public CanaryMod() {
        // Initialize the loader and scan for plugins
        this.loader = new PluginLoader();
        this.loader.scanPlugins();
        this.hookExecutor = new HookExecutor();
        /*
         * TODO: Due to its dynamic nature, each hook requires a delegate.
         * We would need a place where all the canary native hooks are registered along with their delegates
         */
        hookExecutor.registerHook(Hook.Type.CHAT, new ChatDelegate());
    }

    @Override
    public Backbone getBackbone(Backbone.System arg0) {
        // TODO Auto-generated method stub
        return null;
    }
}
