package com.zsd.example.controller;

import java.util.HashMap;
import java.util.Map;

import com.zsd.comm.orm.Page;
import com.zsd.comm.utils.JsonMapper;
import com.zsd.example.domain.Example;
import com.zsd.example.service.ExampleService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 实例控制器.
 * @author zhousd
 */
@Controller
@RequestMapping("example")
public class ExampleController {
    private static Logger logger = LoggerFactory.getLogger(ExampleController.class);

    @Autowired
    private ExampleService service;
    private JsonMapper jsonMapper = new JsonMapper();

    /**
     * 查询列表.
     */
    @RequestMapping(value = "gotoPage", method = RequestMethod.GET)
    public ModelAndView gotoPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("example/list");
        return mv;
    }
    /**
     * 分页
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "search", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String search(@RequestParam(required = false) String name,
        @RequestParam(required = false) String gender,
        @RequestParam(required = false) Integer pageNo,
        @RequestParam(required = false) Integer pageSize) {

        Page<Example> page = new Page<>();
        if (pageNo != null && pageSize != null) {
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
        }

        Map<String, Object> param = new HashMap<>();
        param.put("name", name);
        param.put("gender", gender);

        page = service.search(page, param);


        String ret = jsonMapper.toJson(page);
        return ret;
    }

    /**
     * 跳转页面.
     * @return 测试连接
     */
    @RequestMapping(value = "aaa", method = RequestMethod.GET)
    public ModelAndView ttt() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("ttt", "000");
        mv.setViewName("example/aaa");
        return mv;
    }

    /**
     * 前端首页.
     */
    @RequestMapping(value="frontSet/index", method=RequestMethod.GET)
    public String frontSetIndex(){
        return "frontSet/index";
    }

    /**
     * 前端头部.
     */
    @RequestMapping(value="frontSet/header", method=RequestMethod.GET)
    public String frontSetHeader(){
        return "frontSet/header";
    }

    /**
     * 前端目录页面--以后从opus中读取.
     */
    @RequestMapping(value="frontSet/leftmenu", method=RequestMethod.GET)
    public String frontSetLeftmenu() {
        return "frontSet/leftmenu";
    }

    /**
     * 建设中的页面.
     */
    @RequestMapping(value="todo", method=RequestMethod.GET)
    public String redirectError() {
        return "error/todo";
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
        logger.debug("ExampleController save json : {}", json);
        Map<String, Object> response = new HashMap<>();
        try {
            Example example = jsonMapper.fromJson(json, Example.class);
            if (StringUtils.isNotBlank(example.getId())) {
                service.update(example);
            } else {
                service.save(example);
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
    public Map<String, Object> delete(String idStr) {
        logger.debug("ExampleController delete id : {}", idStr);
        Map<String, Object> response = new HashMap<>();
        try {
            service.delete(idStr);
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

}
