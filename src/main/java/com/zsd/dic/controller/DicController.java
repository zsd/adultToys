package com.zsd.dic.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zsd.comm.utils.JsonMapper;
import com.zsd.dic.domain.Dic;
import com.zsd.dic.service.DicService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static com.zsd.comm.context.ServletContextHolder.getRequest;


/**
 * 字典管理控制器.
 * @author wupb
 */
@Controller
@RequestMapping("dic")
public class DicController {
    private static Logger logger = LoggerFactory.getLogger(DicController.class);

    @Autowired
    private DicService service;
    private JsonMapper jsonMapper = new JsonMapper();

    /**
     * 查询列表.
     */
    @RequestMapping(value = "gotoPage", method = RequestMethod.GET)
    public ModelAndView gotoPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("dic/list");
        return mv;
    }
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView gotoPageOne() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("dic/list");
        return mv;
    }

    /**
     * 保存数据
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(String json) {
        logger.debug("DicController save json : {}", json);
        Map<String, Object> response = new HashMap<>();
        try {
            Dic dic = jsonMapper.fromJson(json, Dic.class);
            if (StringUtils.isNotBlank(dic.getId())) {
                service.update(dic);
            } else {
                service.save(dic);
            }

            response.put("code", "200");
            response.put("msg", "保存成功!");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e.getMessage());
            response.put("code", "500");
            response.put("msg", "保存失败!");
            return response;
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(String id) {
        logger.debug("DicController delete id : {}", id);
        Map<String, Object> response = new HashMap<>();
        try {
            service.delete(id);
            response.put("code", "200");
            response.put("msg", "删除成功!");
            return response;
        } catch (Exception e) {
            logger.debug(e.getMessage());
            response.put("code", "500");
            response.put("msg", "删除失败!");
            return response;
        }
    }


    /**
     * 分页
     */
    @RequestMapping(value = "search",produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String search(String id ) {
        List<Dic> list = service.getByParentId(id);
        String ret = jsonMapper.toJson(list);
        return ret;
    }

    /**
     * 分页
     */
    @RequestMapping(value = "submit",produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String submit(String item ) {
        try{
            //汉字转换
            item = java.net.URLDecoder.decode(getRequest().getParameter("item"),"UTF-8");
            List<Dic> list = service.getByItem(item);
            String ret = jsonMapper.toJson(list);
            return ret;
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
