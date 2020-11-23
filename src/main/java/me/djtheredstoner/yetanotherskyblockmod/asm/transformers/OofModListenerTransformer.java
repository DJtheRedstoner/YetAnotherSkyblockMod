package me.djtheredstoner.yetanotherskyblockmod.asm.transformers;

import me.djtheredstoner.yetanotherskyblockmod.asm.ITransformer;
import org.objectweb.asm.tree.*;

import java.util.ListIterator;

import static org.objectweb.asm.Opcodes.*;

public class OofModListenerTransformer implements ITransformer {
    @Override
    public String[] getClassName() {
        return new String[]{"us.nickfraction.oofmod.listeners.OofModListener"};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        classNode.interfaces.add("me/djtheredstoner/yetanotherskyblockmod/asm/interfaces/IOofModListener");

        for (MethodNode method : classNode.methods) {
            if (method.name.equals("<init>")) {
                ListIterator<AbstractInsnNode> iterator = method.instructions.iterator();

                while (iterator.hasNext()) {
                    AbstractInsnNode next = iterator.next();
                    if(next.getOpcode() == RETURN) {
                        method.instructions.insertBefore(next, insertOofModListener());
                        break;
                    }
                }
            } else if (method.name.equals("playOofSound")) {
                method.access = ACC_PUBLIC;
            }
        }
    }

    public InsnList insertOofModListener() {
        InsnList list = new InsnList();
        list.add(new VarInsnNode(ALOAD, 0));
        list.add(new FieldInsnNode(PUTSTATIC, "me/djtheredstoner/yetanotherskyblockmod/listeners/YASMListener", "oofModListener", "Lme/djtheredstoner/yetanotherskyblockmod/asm/interfaces/IOofModListener;"));
        return list;
    }
}
