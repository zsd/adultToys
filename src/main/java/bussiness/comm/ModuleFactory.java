package bussiness.comm;

import com.zsd.comm.domain.Module;

import java.util.HashMap;
import java.util.Map;

/**
 * 模块管理工厂.
 * @author zhousd
 */
public final class ModuleFactory {

    public static final String ID_EXAMPLE = "example"; // 模块编号

    private static Map<String, Module> moduleMap = new HashMap(); // 模块容器

    static {
        Module m1 = new Module(ID_EXAMPLE, "示例模块");
        moduleMap.put(ID_EXAMPLE, m1);
    }

    /**
     * 根据模块ID获取模块实体.
     */
    public static Module getModule(String moduleId) {
        return moduleMap.get(moduleId);
    }

    private ModuleFactory() {}
}
