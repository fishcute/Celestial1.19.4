package fishcute.celestial.version.dependent.mixin.api;

import fishcute.celestialmain.api.minecraft.wrappers.IQuaternionWrapper;
import org.apache.commons.math3.complex.Quaternion;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Quaternion.class)
public class QuaternionMixin implements IQuaternionWrapper {
}
