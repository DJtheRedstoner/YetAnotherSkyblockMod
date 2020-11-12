package me.djtheredstoner.yetanotherskyblockmod.asm.transformers;

import me.djtheredstoner.yetanotherskyblockmod.asm.ClassTransformer;
import me.djtheredstoner.yetanotherskyblockmod.asm.ITransformer;
import org.objectweb.asm.tree.*;

import java.util.ListIterator;

import static org.objectweb.asm.Opcodes.*;

public class GuiIngameForgeTransformer implements ITransformer {

    @Override
    public String[] getClassName() {
        return new String[]{"net.minecraftforge.client.GuiIngameForge"};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode method : classNode.methods) {
            if (method.name.equals("renderHealth")) {

                ListIterator<AbstractInsnNode> iterator = method.instructions.iterator();

                while (iterator.hasNext()) {
                    AbstractInsnNode next = iterator.next();

                    if(next.getOpcode() == INVOKEVIRTUAL && next.getNext().getOpcode() == FSTORE) {
                        MethodInsnNode insn = (MethodInsnNode) next;

                        String insnMethodName = mapMethodNameFromNode(insn);
                        String insnClassName = mapClassName(insn.owner);

                        if(insnClassName.equals("net/minecraft/entity/player/EntityPlayer")
                            && (insnMethodName.equals("getAbsorptionAmount") || insnMethodName.equals("func_110139_bj"))
                            && insn.desc.equals("()F")) {
                            method.instructions.insert(insn, insertGuiIngameForgeHook());
                        }

                    }
                }

            }
        }
    }

    public InsnList insertGuiIngameForgeHook() {
        InsnList list = new InsnList();
        list.add(new LdcInsnNode("guiIngameAbsorption"));
        list.add(new MethodInsnNode(INVOKESTATIC, ClassTransformer.hookClass, "isSkyblock", "(Ljava/lang/String;)Z", false));
        LabelNode falseLabel = new LabelNode();
        list.add(new JumpInsnNode(IFEQ, falseLabel));
        list.add(new InsnNode(POP));
        list.add(new InsnNode(FCONST_0));
        list.add(falseLabel);
        return list;
    }
}
