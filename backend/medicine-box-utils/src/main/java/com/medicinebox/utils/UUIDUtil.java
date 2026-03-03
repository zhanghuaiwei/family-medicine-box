package com.medicinebox.utils;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

/**
 * UUID工具类
 */
public class UUIDUtil {

    /**
     * 生成UUID
     * @return UUID字符串
     */
    public static String generateUUID() {
        UUID uuid = Generators.timeBasedGenerator().generate();
        return uuid.toString().replace("-", "");
    }

    /**
     * 生成带连字符的UUID
     * @return 带连字符的UUID字符串
     */
    public static String generateUUIDWithHyphen() {
        UUID uuid = Generators.timeBasedGenerator().generate();
        return uuid.toString();
    }

    /**
     * 验证字符串是否为有效的UUID
     * @param uuid 字符串
     * @return 是否为有效的UUID
     */
    public static boolean isValidUUID(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
