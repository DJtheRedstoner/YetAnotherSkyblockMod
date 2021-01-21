package me.djtheredstoner.yetanotherskyblockmod.asm.transformers;

import me.djtheredstoner.yetanotherskyblockmod.asm.ClassTransformer;
import me.djtheredstoner.yetanotherskyblockmod.asm.ITransformer;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class CanelexKeystrokesTransformer implements ITransformer {
    @Override
    public String[] getClassName() {
        return new String[]{"net.canelex.keystrokes.KeystrokesMod"};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode method : classNode.methods) {
            if(method.name.equals("renderGUI")) {
                method.instructions.insert(insertCanelexKeystrokesHook());
            }
        }
    }

    public InsnList insertCanelexKeystrokesHook() {
        InsnList list = new InsnList();
        list.add(new LdcInsnNode("canelexKeystrokes"));
        list.add(new MethodInsnNode(INVOKESTATIC, ClassTransformer.hookClass, "isSkyblock", "(Ljava/lang/String;)Z", false));
        LabelNode falseLabel = new LabelNode();
        list.add(new JumpInsnNode(IFEQ, falseLabel));
        list.add(new InsnNode(RETURN));
        list.add(falseLabel);
        return list;
    }
}
