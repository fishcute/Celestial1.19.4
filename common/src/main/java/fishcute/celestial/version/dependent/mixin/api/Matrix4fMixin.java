package fishcute.celestial.version.dependent.mixin.api;

import fishcute.celestialmain.api.minecraft.wrappers.IMatrix4fWrapper;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Matrix4f.class)
public class Matrix4fMixin implements IMatrix4fWrapper {

}
