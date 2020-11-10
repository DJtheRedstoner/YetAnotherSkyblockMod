package me.djtheredstoner.yetanotherskyblockmod.asm.transformers;

import me.djtheredstoner.yetanotherskyblockmod.asm.ClassTransformer;
import me.djtheredstoner.yetanotherskyblockmod.asm.ITransformer;
import org.objectweb.asm.tree.*;

import java.util.ListIterator;

import static org.objectweb.asm.Opcodes.*;

public class PownsHudRendererTransformer implements ITransformer {
    @Override
    public String[] getClassName() {
        return new String[]{"me.powns.potionhud.gui.HudRenderer"};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode method : classNode.methods) {
            if(method.name.equals("onHudRender")) {

                ListIterator<AbstractInsnNode> iterator = method.instructions.iterator();

                while (iterator.hasNext()) {
                    AbstractInsnNode next = iterator.next();

                    if(next.getOpcode() == GETFIELD && next.getNext().getOpcode() == IFNULL) {
                        FieldInsnNode insn = (FieldInsnNode) next;
                       if(insn.owner.equals("net/minecraft/client/Minecraft") && insn.desc.equals("Lnet/minecraft/client/gui/GuiScreen;")) {
                            method.instructions.insertBefore(insn.getPrevious().getPrevious().getPrevious(), insertPownsHook());
                       }
                    }
                }

            }
        }
    }

    private InsnList insertPownsHook() {
        InsnList list = new InsnList();
        list.add(new LdcInsnNode("pownsPotionHud"));
        list.add(new MethodInsnNode(INVOKESTATIC, ClassTransformer.hookClass, "isSkyblock", "(Ljava/lang/String;)Z", false));
        LabelNode falseLabel = new LabelNode();
        list.add(new JumpInsnNode(IFEQ, falseLabel));
        list.add(new InsnNode(RETURN));
        list.add(falseLabel);
        return list;
    }
}
