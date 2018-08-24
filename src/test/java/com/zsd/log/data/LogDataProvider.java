package com.zsd.log.data;

import bussiness.comm.ClientFactory;
import bussiness.comm.ModuleFactory;
import com.zsd.example.domain.Example;
import com.zsd.comm.domain.Client;
import com.zsd.log.domain.Log;
import com.zsd.comm.domain.Module;
import com.zsd.user.data.UserDataProvider;
import com.zsd.user.domain.User;

/**
 * 业务日志测试数据提供类.
 * @author zhousd
 */
public final class LogDataProvider {

    /**
     * 生成业务日志实体.
     */
    public static Log getLog() {
        Module module = ModuleFactory.getModule(ModuleFactory.ID_EXAMPLE);
        Example obj = new Example();
        obj.setId("1");
        obj.setName("测试示例");

        User user = UserDataProvider.getUser();
        Client client = ClientFactory.getClient(ClientFactory.TYPE_PC_WEB);
        return new Log(Log.TYPE_ADD, "新增一条地名地址信息", "1.1.1.1", module, obj, user, client);
    }

    private LogDataProvider() {}

}
