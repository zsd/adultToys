package bussiness.spider.service.pipeline;

import com.alibaba.fastjson.JSON;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

/**
 * 淘宝数据处理类.
 * @author zhousd
 */
public class TaobaoJsonPipeline extends FilePersistentBase implements Pipeline {

    public TaobaoJsonPipeline(String path) {
        this.setPath(path);
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        try {
            Iterator iterator = resultItems.getAll().values().iterator();
            while (iterator.hasNext()) {
                Map map = (Map) iterator.next();
                String name = map.get("itemId").toString();
                if (map.get("raw_title") == null) {
                    if (map.get("rateList")!=null)
                        name += "_tmall_comment";
                    else name += "_taobao_comment";
                    name+="_"+map.get("currentPage");
                }
                PrintWriter printWriter = new PrintWriter(new FileWriter(this.getFile(path + name + ".json")));
                printWriter.write(JSON.toJSONString(map));
                printWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
