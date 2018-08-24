package bussiness.comm;

import com.zsd.comm.domain.ObjectType;

import java.util.HashMap;
import java.util.Map;

/**
 * 实体类型工厂.
 * @author zhousd
 */
public class ObjectTypeFactory {

    public static final String ID_EXAMPLE = "1001"; // 示例实体编号

    private static Map<String, ObjectType> objectTypeMap = new HashMap(); // 实体容器

    static {
        ObjectType ot1 = new ObjectType(ID_EXAMPLE, "示例对象");
        objectTypeMap.put(ID_EXAMPLE, ot1);
    }

    /**
     * 根据实体ID获取实体类型.
     */
    public static ObjectType getObjectType(String objectTypeId) {
        return objectTypeMap.get(objectTypeId);
    }

    private ObjectTypeFactory() {}
}
