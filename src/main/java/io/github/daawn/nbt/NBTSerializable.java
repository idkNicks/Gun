package io.github.daawn.nbt;

import net.minecraft.nbt.CompoundTag;

public interface NBTSerializable {

    void writeToNBT(CompoundTag compound);

    void readFromNBT(CompoundTag compound);
}