package fishcute.celestialforge;

import com.mojang.blaze3d.platform.InputConstants;
import fishcute.celestial.CelestialClient;
import fishcute.celestial.version.dependent.VInstances;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("celestial")
public class CelestialForge {

    private static KeyMapping reloadSky = new KeyMapping(
            "key.reload_sky",
            InputConstants.KEY_F10,
            "key.categories.misc"
    );

    @SubscribeEvent
    public void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(reloadSky);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            while (reloadSky.consumeClick()) {
                CelestialClient.onReloadKey();
            }
        }
    }

    public CelestialForge() {
        VInstances.setInstances();
    }
}