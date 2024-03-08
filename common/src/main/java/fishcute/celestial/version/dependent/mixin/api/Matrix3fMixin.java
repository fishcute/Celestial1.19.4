package fishcute.celestial.version.dependent.mixin.api;

import fishcute.celestialmain.api.minecraft.wrappers.IMatrix3fWrapper;
import org.joml.Matrix3f;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Matrix3f.class)
public class Matrix3fMixin implements IMatrix3fWrapper {
}
