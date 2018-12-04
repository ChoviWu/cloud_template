package org.github..common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.CRC32;

/**
 * @author
 * @date 2018/6/27
 * Description : 序列化反序列化工具
 */
@Slf4j
public class SerializeUtils {


    public static Object unserialize(byte[] bytes){
        ObjectInputStream ois = null;
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            Object obj = ois.readObject();
            return obj;
        }catch (Exception e){
            log.info("unserialize error :" + e.getMessage());
        }
        return null;
    }
    /**
     * 序列化
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            log.info("serialize error :" + e.getMessage());
        }
        return null;
    }
    //对消息体crc32处理防止消息重复
    public static String crc32Code(byte[] bytes) {
        CRC32 crc32 = new CRC32();
        crc32.update(bytes);
        return Long.valueOf(crc32.getValue()).toString();
    }
}
