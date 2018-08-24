package com.zsd.function.controller;

import com.zsd.comm.utils.JsonMapper;
import com.zsd.function.domain.Function;
import com.zsd.function.service.FunctionService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 黄辅湘 on 2016/6/22.
 */
@Controller
@RequestMapping("function")
public class FunctionController {
    private static Logger logger = LoggerFactory.getLogger(FunctionController.class);
    @Autowired
    private FunctionService functionService ;

    private JsonMapper jsonMapper = new JsonMapper();


    @RequestMapping(value = "gotoPage", method = RequestMethod.GET)
    public ModelAndView gotoPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("function/list");
        return mv;
    }

    /**
     * 分页
     */
    @RequestMapping(value = "search",produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String search(String id ) {
        List<Function> list = functionService.getByParentId(id);
        String ret = jsonMapper.toJson(list);
        return ret;
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
        logger.debug("FunctionController save json : {}", json);
        Map<String, Object> response = new HashMap<>();
        try {
            Function function = jsonMapper.fromJson(json, Function.class);
            long  c = functionService.checkedCode(function.getId(),function.getCode());
            if(c==0){
                if (StringUtils.isNotBlank(function.getId())) {
                    functionService.update(function);
                } else {
                    functionService.save(function);
                }
                response.put("code", "200");
                response.put("msg", "保存成功!");
            }else{
                response.put("code", "500");
                response.put("msg", "保存失败,编号已经存在!");
            }



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
        logger.debug("FunctionController delete id : {}", id);
        Map<String, Object> response = new HashMap<>();
        try {
            functionService.delete(id);
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
