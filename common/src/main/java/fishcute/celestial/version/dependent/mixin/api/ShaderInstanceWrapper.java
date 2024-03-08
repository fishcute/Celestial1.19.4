package fishcute.celestial.version.dependent.mixin.api;

import fishcute.celestialmain.api.minecraft.wrappers.IShaderInstanceWrapper;
import net.minecraft.client.renderer.ShaderInstance;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ShaderInstance.class)
public class ShaderInstanceWrapper implements IShaderInstanceWrapper {
}
