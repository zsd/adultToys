package bussiness.comm;

import com.zsd.comm.context.SecurityContextHolder;
import com.zsd.comm.context.ServletContextHolder;
import com.zsd.comm.domain.Client;
import com.zsd.comm.domain.EntityImpl;
import com.zsd.comm.domain.Module;
import com.zsd.log.domain.Log;
import com.zsd.user.domain.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by msx on 2016/6/21.
 * 系统日志工厂类
 */
public final class LogFactory {

    /**
     * 添加系统日志
     * @param logType 日志类型
     * @param logName 日志描述
     * @param moduleName 日志模块名称
     * @param entity 操作实体
     */
    public static Log insert(int logType, String logName,String moduleName,EntityImpl entity){
        //添加系统日记
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = ServletContextHolder.getRequest().getRemoteAddr();
        User post = SecurityContextHolder.getUser();
        Module module = new Module();
        module.setName(moduleName);
        Client client = new Client();
        client.setVersion( request.getHeader("user-agent"));
        Log log = new Log(logType, logName, ip, module, entity, post, client);
        return log;
    }
}
