package fishcute.celestial.mixin;

import fishcute.celestialmain.version.independent.VersionSky;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DimensionSpecialEffects.class)
public class DimensionSpecialEffectsMixin {

    @Inject(method = "getCloudHeight", at = @At("RETURN"), cancellable = true)
    private void getCloudHeight(CallbackInfoReturnable<Float> info) {
        info.setReturnValue(VersionSky.getCloudHeight(info.getReturnValue()));
    }
    @Inject(method = "getSunriseColor", at = @At("RETURN"), cancellable = true)
    private void getFogColorOverride(float skyAngle, float tickDelta, CallbackInfoReturnable<float[]> info) {
        info.setReturnValue(new float[]{0, 0, 0, 0});
    }
}
