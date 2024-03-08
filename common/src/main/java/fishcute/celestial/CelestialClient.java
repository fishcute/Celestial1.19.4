package fishcute.celestial;

import fishcute.celestialmain.util.ClientTick;
import fishcute.celestialmain.util.Util;

public class CelestialClient {

    public void onInitializeClient() {
        Util.log("Loading Celestial");
    }

    public static void onReloadKey() {
        ClientTick.onReloadKey();
    }
}