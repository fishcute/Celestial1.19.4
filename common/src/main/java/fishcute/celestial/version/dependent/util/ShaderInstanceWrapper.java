package fishcute.celestial.version.dependent.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.ShaderInstance;

public class ShaderInstanceWrapper {
    public ShaderInstance shaderInstance;
    public ShaderInstanceWrapper() {
        this.shaderInstance = RenderSystem.getShader();
    }
}
