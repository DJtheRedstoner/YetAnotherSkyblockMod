package me.djtheredstoner.yetanotherskyblockmod.asm.transformers;

import me.djtheredstoner.yetanotherskyblockmod.asm.ClassTransformer;
import me.djtheredstoner.yetanotherskyblockmod.asm.ITransformer;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class PatcherArmorStatusRendererTransformer implements ITransformer {
    @Override
    public String[] getClassName() {
        return new String[]{"club.sk1er.patcher.util.armor.ArmorStatusRenderer"};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode method : classNode.methods) {
            if(method.name.equals("onRenderArmor")) {
                method.instructions.insert(insertPatcherArmor());
                break;
            }
        }
    }

    public InsnList insertPatcherArmor() {
        InsnList list = new InsnList();
        list.add(new LdcInsnNode("patcherArmor"));
        list.add(new MethodInsnNode(INVOKESTATIC, ClassTransformer.hookClass, "isSkyblock", "(Ljava/lang/String;)Z", false));
        LabelNode falseLabel = new LabelNode();
        list.add(new JumpInsnNode(IFEQ, falseLabel));
        list.add(new InsnNode(RETURN));
        list.add(falseLabel);
        return list;
    }
}
