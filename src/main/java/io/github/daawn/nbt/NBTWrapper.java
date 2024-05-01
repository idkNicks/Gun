package io.github.daawn.nbt;

import net.minecraft.nbt.CompoundTag;

public interface NBTWrapper {

    <T extends NBTSerializable> T getObject(String path, Class<T> clazz, CompoundTag parentTag) throws InstantiationException, IllegalAccessException;

    void setObject(String path, NBTSerializable value, CompoundTag parentTag);

    String getString(String path);

    void setString(String path, String value);

    char getChar(String path);

    void setChar(String path, char value);

    byte getByte(String path);

    void setByte(String path, byte value);

    short getShort(String path);

    void setShort(String path, short value);

    int getInt(String path);

    void setInt(String path, int value);

    long getLong(String path);

    void setLong(String path, long value);

    float getFloat(String path);

    void setFloat(String path, float value);

    double getDouble(String path);

    void setDouble(String path, double value);

    boolean getBoolean(String path);

    void setBoolean(String path, boolean value);

    boolean hasTag();
}