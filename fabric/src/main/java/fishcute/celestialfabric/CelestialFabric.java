package fishcute.celestialfabric;

import fishcute.celestialmain.util.ClientTick;
import fishcute.celestialmain.util.Util;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

public class CelestialFabric implements ClientModInitializer {
    public static KeyMapping reloadSky;
    public static KeyMapping devToggleSky;
    @Override
    public void onInitializeClient() {
        Util.log("Loading Celestial Fabric");

        reloadSky = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.reload_sky",
                InputConstants.KEY_F10,
                "key.categories.misc"
        ));

        ClientTickEvents.END_CLIENT_TICK.register((endTick -> {
            while (reloadSky.consumeClick()) {
                ClientTick.onReloadKey();
            }
        }));
    }
}