package fishcute.celestial;

import fishcute.celestialmain.util.Util;
import net.fabricmc.api.ClientModInitializer;

public class CelestialClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Util.log("Loading Celestial");
    }
}