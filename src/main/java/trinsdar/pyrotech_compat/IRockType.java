package trinsdar.pyrotech_compat;

import net.minecraft.item.ItemStack;

public interface IRockType {
    ItemStack asStack();

    ItemStack asStack(int amount);

    IRockType fromMeta(int meta);
}
