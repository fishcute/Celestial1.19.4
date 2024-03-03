package fishcute.celestial.version.dependent.util;

import com.mojang.blaze3d.vertex.PoseStack;
import fishcute.celestial.version.dependent.VMath;
import fishcute.celestial.version.dependent.mixin.PoseMixin;
import fishcute.celestial.version.dependent.mixin.PoseStackMixin;
import org.apache.commons.math3.complex.Quaternion;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class PoseStackWrapper {
    private static final Vector3f XN = new Vector3f(-1.0F, 0.0F, 0.0F);
    private static final Vector3f YN = new Vector3f(0.0F, -1.0F, 0.0F);
    private static final Vector3f ZN = new Vector3f(0.0F, 0.0F, -1.0F);

    public PoseStack matrices;
    public PoseStackWrapper(PoseStack matrices) {
        this.matrices = matrices;
    }

    public Matrix4fWrapper lastPose() {
        return new Matrix4fWrapper(matrices.last().pose());
    }
    public void pushPose() {
        matrices.pushPose();
    }
    public void popPose() {
        matrices.popPose();
    }
    public void translate(double x, double y, double z) {
        matrices.translate(x, y, z);
    }
    public void mulPose(Axis a, float rot) {
        switch (a) {
            case X:
                VMath.matrix3fSetAxisAngle(((Matrix3f)((Object) int3)), XN, rot);
                this.mulPose(int3, int4);
                break;
            case Y:
                VMath.matrix3fSetAxisAngle(((Matrix3f)((Object) int3)), YN, rot);
                this.mulPose(int3, int4);
                break;
            case Z:
                VMath.matrix3fSetAxisAngle(((Matrix3f)((Object) int3)), ZN, rot);
                this.mulPose(int3, int4);
                break;
        }
    }

    public void mulPose(Quaternion quaternion) {
        this.mulPose(quaternion, int4, int3);
    }

    static Matrix4f int4 = new Matrix4f();
    static Matrix3f int3 = new Matrix3f();

    public void mulPose(Quaternion quaternion, Matrix4f intermediate4, Matrix3f intermediate3) {
        PoseStack.Pose pose = ((PoseStackMixin)(Object) this.matrices).getPoseStack().getLast();
        VMath.matrix3fCopyQuaternion(((Matrix3f)(Object) intermediate3), quaternion);
        VMath.matrix4fCopyMatrix3f(((Matrix4f)(Object) intermediate4), intermediate3);

        ((PoseMixin)(Object) pose).getPose().mul(intermediate4);
        ((PoseMixin)(Object) pose).getNormal().mul(intermediate3);
    }

    public void mulPose(Matrix3f matrix3f, Matrix4f intermediate4) {
        PoseStack.Pose pose = ((PoseStackMixin)(Object) this.matrices).getPoseStack().getLast();
        VMath.matrix4fCopyMatrix3f(((Matrix4f)(Object) intermediate4), matrix3f);
        ((PoseMixin)(Object) pose).getPose().mul(intermediate4);
        ((PoseMixin)(Object) pose).getNormal().mul(matrix3f);
    }

    public enum Axis {
        X,
        Y,
        Z
    }

    public Matrix4fWrapper rotate(float i, float j, float k) {
        this.pushPose();

        if (i != 0) {
            this.mulPose(Axis.X, i);
        }
        if (j != 0) {
            this.mulPose(Axis.Y, j);
        }
        if (k != 0) {
            this.mulPose(Axis.Z, k);
        }

        Matrix4fWrapper matrix4f = this.lastPose();
        this.popPose();

        return matrix4f;
    }

    public Matrix4fWrapper rotateThenTranslate(float i, float j, float k, float x, float y, float z) {
        this.pushPose();

        if (i != 0) {
            this.mulPose(Axis.X, i);
        }
        if (j != 0) {
            this.mulPose(Axis.Y, j);
        }
        if (k != 0) {
            this.mulPose(Axis.Z, k);
        }

        this.translate(x, y, z);

        Matrix4fWrapper matrix4f = this.lastPose();
        this.popPose();

        return matrix4f;
    }

    public Matrix4fWrapper translateThenRotate(float i, float j, float k, float x, float y, float z) {
        this.pushPose();

        this.translate(x, y, z);

        if (i != 0) {
            this.mulPose(Axis.X, i);
        }
        if (j != 0) {
            this.mulPose(Axis.Y, j);
        }
        if (k != 0) {
            this.mulPose(Axis.Z, k);
        }

        Matrix4fWrapper matrix4f = this.lastPose();
        this.popPose();

        return matrix4f;
    }
}
