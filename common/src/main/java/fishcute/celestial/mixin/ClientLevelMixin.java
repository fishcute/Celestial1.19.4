package fishcute.celestial.mixin;

import fishcute.celestial.Vector;
import fishcute.celestialmain.version.independent.VersionSky;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {
    @Inject(method = "getCloudColor", at = @At("RETURN"), cancellable = true)
    private void getCloudColor(float f, CallbackInfoReturnable<Vec3> info) {
        float[] color = VersionSky.getCloudColor(new float[]{
                (float) info.getReturnValue().x,
                (float) info.getReturnValue().y,
                (float) info.getReturnValue().z
        });
        info.setReturnValue(Vector.toVecFromArray(color));
    }
    @Inject(method = "getSkyColor", at = @At("RETURN"), cancellable = true)
    private void getSkyColor(Vec3 vec3, float f, CallbackInfoReturnable<Vec3> info) {
        float[] color = VersionSky.getClientLevelSkyColor(new float[]{(float) info.getReturnValue().x, (float) info.getReturnValue().y, (float) info.getReturnValue().z});
        info.setReturnValue(Vector.toVecFromArray(color));
    }
}
