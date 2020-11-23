package me.djtheredstoner.yetanotherskyblockmod.asm.transformers;

import me.djtheredstoner.yetanotherskyblockmod.asm.ClassTransformer;
import me.djtheredstoner.yetanotherskyblockmod.asm.ITransformer;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class PatcherHotbarItemsHandlerTransformer implements ITransformer {
    @Override
    public String[] getClassName() {
        return new String[]{"club.sk1er.patcher.util.hotbar.HotbarItemsHandler"};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode method : classNode.methods) {
            switch (method.name) {
                case "renderDamage":
                    method.instructions.insert(insertPatcherHotbar("patcherDamageGlance"));
                    break;
                case "renderItemCount":
                    method.instructions.insert(insertPatcherHotbar("patcherItemCountGlance"));
                    break;
                case "renderEnchantments":
                    method.instructions.insert(insertPatcherHotbar("patcherEnchGlance"));
                    break;
            }
        }
    }

    public InsnList insertPatcherHotbar(String mod) {
        InsnList list = new InsnList();
        list.add(new LdcInsnNode(mod));
        list.add(new MethodInsnNode(INVOKESTATIC, ClassTransformer.hookClass, "isSkyblock", "(Ljava/lang/String;)Z", false));
        LabelNode falseLabel = new LabelNode();
        list.add(new JumpInsnNode(IFEQ, falseLabel));
        list.add(new InsnNode(RETURN));
        list.add(falseLabel);
        return list;
    }
}
