package fishcute.celestial.version.dependent.mixin.api;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import fishcute.celestialmain.api.minecraft.wrappers.IBufferBuilderWrapper;
import fishcute.celestialmain.api.minecraft.wrappers.IMatrix4fWrapper;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BufferBuilder.class)
public class BufferBuilderMixin implements IBufferBuilderWrapper {
    @Override
    public void celestial$beginTriangleFan() {
        var self = (BufferBuilder)(Object) this;
        self.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);;
    }

    @Override
    public void celestial$beginObject() {
        var self = (BufferBuilder)(Object) this;
        self.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
    }

    @Override
    public void celestial$beginColorObject() {
        var self = (BufferBuilder)(Object) this;
        self.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
    }

    @Override
    public void celestial$vertex(IMatrix4fWrapper matrix4f, float x, float y, float z, float r, float g, float b, float a) {
        var self = (BufferBuilder)(Object) this;
        self.vertex((Matrix4f) matrix4f, x, y, z).color(r, g, b, a).endVertex();
    }

    @Override
    public void celestial$vertexUv(IMatrix4fWrapper matrix4f, float x, float y, float z, float u, float v, float r, float g, float b, float a) {
        var self = (BufferBuilder)(Object) this;
        self.vertex((Matrix4f) matrix4f, x, y, z).uv(u, v).color(r, g, b, a).endVertex();
    }

    @Override
    public void celestial$upload() {
        var self = (BufferBuilder)(Object) this;
        BufferUploader.drawWithShader(self.end());
    }

//    @Override
//    public IBufferBuilderWrapper celestial$init() {
//        return null;
//    }
}
