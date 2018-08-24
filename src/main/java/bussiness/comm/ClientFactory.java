package bussiness.comm;

import com.zsd.comm.domain.Client;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户端工厂类.
 * @author zhousd
 */
public final class ClientFactory {

    public static final int TYPE_PC_WEB = 0; // Web端
    public static final int TYPE_MB_ANDROID = 1; // 手机端(安卓)
    public static final int TYPE_MB_IOS = 2; // 手机端(IOS)

    private static Map<Integer, Client> clientMap = new HashMap();

    static {
        Client androidClient = new Client("android", TYPE_MB_ANDROID, "1.0");
        clientMap.put(TYPE_MB_ANDROID, androidClient);
        Client iosClient = new Client("ios", TYPE_MB_IOS, "1.0");
        clientMap.put(TYPE_MB_IOS, iosClient);
        Client pcClient = new Client("web", TYPE_PC_WEB, "1.0");
        clientMap.put(TYPE_PC_WEB, pcClient);
    }

    /**
     * 根据客户端类型获取客户端实体.
     * @param clientType 客户端类型
     * @return 客户端实体
     */
    public static Client getClient(int clientType) {
        return clientMap.get(clientType);
    }

    private ClientFactory() {}
}
