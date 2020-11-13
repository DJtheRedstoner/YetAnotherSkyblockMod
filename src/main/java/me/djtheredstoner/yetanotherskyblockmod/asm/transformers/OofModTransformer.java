package me.djtheredstoner.yetanotherskyblockmod.asm.transformers;

import me.djtheredstoner.yetanotherskyblockmod.asm.ITransformer;
import org.objectweb.asm.tree.*;

import java.util.ListIterator;

import static org.objectweb.asm.Opcodes.*;

public class OofModTransformer implements ITransformer {
    @Override
    public String[] getClassName() {
        return new String[]{"us.nickfraction.oofmod.OofMod"};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        classNode.fields.add(new FieldNode(ACC_PUBLIC | ACC_STATIC, "listener", "Lus/nickfraction/oofmod/listeners/OofModListener;", null, null));

        for (MethodNode method : classNode.methods) {

            if(method.name.equals("init")) {
                ListIterator<AbstractInsnNode> iterator = method.instructions.iterator();

                while (iterator.hasNext()) {
                    AbstractInsnNode next = iterator.next();

                    if(next.getOpcode() == NEW && ((TypeInsnNode) next).desc.equals("us/nickfraction/oofmod/listeners/OofModListener")) {
                        method.instructions.insert(next.getNext().getNext().getNext(), insertOofModHook());
                        break;
                    }
                }
            }
        }
    }

    public InsnList insertOofModHook() {
        InsnList list = new InsnList();
        list.add(new InsnNode(DUP));
        list.add(new FieldInsnNode(PUTSTATIC, "us/nickfraction/oofmod/OofMod", "listener", "Lus/nickfraction/oofmod/listeners/OofModListener;"));
        return list;
    }
}
