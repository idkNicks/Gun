package io.github.daawn.nbt;

import net.minecraft.nbt.CompoundTag;

public class NBTCompound implements NBTWrapper {

    protected CompoundTag compoundTag;

    public NBTCompound() {
        this.compoundTag = new CompoundTag();
    }

    @Override
    public void setObject(String path, NBTSerializable value, CompoundTag parentTag) {
        CompoundTag compound = new CompoundTag();
        value.writeToNBT(compound);
        parentTag.put(path, compound);
    }

    @Override
    public <T extends NBTSerializable> T getObject(String path, Class<T> clazz, CompoundTag parentTag) throws InstantiationException, IllegalAccessException {
        CompoundTag compound = parentTag.getCompound(path);
        T object = clazz.newInstance();
        object.readFromNBT(compound);
        return object;
    }

    @Override
    public String getString(String path) {
        return compoundTag.getString(path);
    }

    @Override
    public void setString(String path, String value) {
        compoundTag.putString(path, value);
    }

    @Override
    public char getChar(String path) {
        return (char) compoundTag.getByte(path);
    }

    @Override
    public void setChar(String path, char value) {
        compoundTag.putByte(path, (byte) value);
    }

    @Override
    public byte getByte(String path) {
        return compoundTag.getByte(path);
    }

    @Override
    public void setByte(String path, byte value) {
        compoundTag.putByte(path, value);
    }

    @Override
    public short getShort(String path) {
        return compoundTag.getShort(path);
    }

    @Override
    public void setShort(String path, short value) {
        compoundTag.putShort(path, value);
    }

    @Override
    public int getInt(String path) {
        return compoundTag.getInt(path);
    }

    @Override
    public void setInt(String path, int value) {
        compoundTag.putInt(path, value);
    }

    @Override
    public long getLong(String path) {
        return compoundTag.getLong(path);
    }

    @Override
    public void setLong(String path, long value) {
        compoundTag.putLong(path, value);
    }

    @Override
    public float getFloat(String path) {
        return compoundTag.getFloat(path);
    }

    @Override
    public void setFloat(String path, float value) {
        compoundTag.putFloat(path, value);
    }

    @Override
    public double getDouble(String path) {
        return compoundTag.getDouble(path);
    }

    @Override
    public void setDouble(String path, double value) {
        compoundTag.putDouble(path, value);
    }

    @Override
    public boolean getBoolean(String path) {
        return compoundTag.getBoolean(path);
    }

    @Override
    public void setBoolean(String path, boolean value) {
        compoundTag.putBoolean(path, value);
    }

    @Override
    public boolean hasTag() {
        return !compoundTag.isEmpty();
    }
}