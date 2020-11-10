package me.djtheredstoner.yetanotherskyblockmod.asm.transformers;

import me.djtheredstoner.yetanotherskyblockmod.asm.ClassTransformer;
import me.djtheredstoner.yetanotherskyblockmod.asm.ITransformer;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class PownsArmorHudRendererTransformer implements ITransformer {
    @Override
    public String[] getClassName() {
        return new String[]{"me.powns.armorhud.gui.HudRenderer"};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode method : classNode.methods) {
            if(method.name.equals("renderHud")) {
                method.instructions.insert(insertPownsArmorHudHook());
            }
        }
    }

    public InsnList insertPownsArmorHudHook() {
        InsnList list = new InsnList();
        list.add(new LdcInsnNode("pownsArmorHud"));
        list.add(new MethodInsnNode(INVOKESTATIC, ClassTransformer.hookClass, "isSkyblock", "(Ljava/lang/String;)Z", false));
        LabelNode falseLabel = new LabelNode();
        list.add(new JumpInsnNode(IFEQ, falseLabel));
        list.add(new InsnNode(RETURN));
        list.add(falseLabel);
        return list;
    }
}
