package fishcute.celestialfabric;


import com.mojang.blaze3d.platform.InputConstants;
import fishcute.celestial.CelestialClient;
import fishcute.celestial.version.dependent.VInstances;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

public class CelestialFabric implements ClientModInitializer {
    public static KeyMapping reloadSky;
    public static KeyMapping devToggleSky;
    @Override
    public void onInitializeClient() {
        VInstances.setInstances();

        reloadSky = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.reload_sky",
                InputConstants.KEY_F10,
                "key.categories.misc"
        ));

        ClientTickEvents.END_CLIENT_TICK.register((endTick -> {
            while (reloadSky.consumeClick()) {
                CelestialClient.onReloadKey();
            }
        }));
    }
}