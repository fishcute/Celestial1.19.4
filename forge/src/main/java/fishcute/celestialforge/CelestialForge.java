package fishcute.celestialforge;

import com.mojang.blaze3d.platform.InputConstants;
import fishcute.celestialmain.util.ClientTick;
import fishcute.celestialmain.util.Util;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("celestial")
public class CelestialForge {
    public CelestialForge() {
        Util.log("Loading Celestial Forge");
    }

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
                ClientTick.onReloadKey();
            }
        }
    }
}