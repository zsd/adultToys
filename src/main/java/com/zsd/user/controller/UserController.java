package com.zsd.user.controller;

import bussiness.comm.LogFactory;
import bussiness.comm.MD5Encryption;
import com.zsd.comm.context.SecurityContextHolder;
import com.zsd.comm.orm.Page;
import com.zsd.comm.utils.ControllerUtils;
import com.zsd.comm.utils.JsonMapper;
import com.zsd.log.domain.Log;
import com.zsd.log.service.LogService;
import com.zsd.user.domain.User;
import com.zsd.user.service.UserService;
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

import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器.
 * @author zhousd
 */
@Controller
@RequestMapping("user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService ;

    private JsonMapper jsonMapper = new JsonMapper();

    /**
     * 查询列表.
     */
    @RequestMapping(value = "gotoPage", method = RequestMethod.GET)
    public ModelAndView gotoPage() {
        ModelAndView mv = new ModelAndView();
        //添加系统日记
        logService.add(LogFactory.insert(Log.TYPE_GETBYID,"访问用户管理页面","机构管理", SecurityContextHolder.getUser()));
        mv.setViewName("user/list");
        return mv;
    }

    /**
     * 分页.
     */
    @RequestMapping(value = "search", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String search(@RequestParam(required = false) String name,
                         @RequestParam(required = false) String gender,
                         @RequestParam(required = false) Integer pageNo,
                         @RequestParam(required = false) Integer pageSize) {

        Page<User> page = new Page<>();
        if (pageNo != null && pageSize != null) {
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
        }

        Map<String, Object> param = new HashMap<>();
        param.put("name", name);
        param.put("gender", gender);

        page = userService.search(page, param);

        return jsonMapper.toJson(page);
    }

    /**
     * 保存数据.
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(String json) {
        logger.debug("OrgController save json : {}", json);
        Map<String, Object> response;
        try {
            User user = jsonMapper.fromJson(json, User.class);
            long count = userService.checkLoginName(user.getId(),user.getLoginName());
            if(count == 0){
                if (StringUtils.isNotBlank(user.getId())) {
                    userService.update(user);
                    //添加系统日记
                    logService.add(LogFactory.insert(Log.TYPE_MOD,"修改用户","机构管理", SecurityContextHolder.getUser()));
                } else {
                    userService.save(user);
                    //添加系统日记
                    logService.add(LogFactory.insert(Log.TYPE_ADD,"添加用户","机构管理", SecurityContextHolder.getUser()));
                }
                response = ControllerUtils.responseBuilder(ControllerUtils.CODE_SUCCESS, "保存成功！");
            }else{
                response = ControllerUtils.responseBuilder(ControllerUtils.CODE_ERROR, "登录名已存在！");
            }

            return response;
        } catch (Exception e) {
            logger.debug(e.getMessage());
            response = ControllerUtils.responseBuilder(ControllerUtils.CODE_ERROR, "保存失败！");
            return response;
        }
    }

    /**
     * 批量删除用户.
     * @param idStr id集合，以逗号隔开
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(String idStr) {
        logger.debug("UserController delete id : {}", idStr);
        Map<String, Object> response;
        try {
            userService.deleteIdStr(idStr);
            //添加系统日记
            logService.add(LogFactory.insert(Log.TYPE_DEL,"删除用户","机构管理", SecurityContextHolder.getUser()));
            response = ControllerUtils.responseBuilder(ControllerUtils.CODE_SUCCESS, "删除成功！");
        } catch (Exception e) {
            logger.debug("delete idStr failed!", e);
            response = ControllerUtils.responseBuilder(ControllerUtils.CODE_ERROR, "删除失败！");
        }
        return response;
    }

    /**
     * 重置密码.
     */
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> resetPassword(String id){
        logger.debug("UserController resetPassword id : ", id);
        Map<String, Object> response;
        try {
            //获取需要重置密码的用户
            User user = userService.getById(id);
            //重置密码为123
            user.setPassword("123");
            //更新信息
            userService.update(user);
            response = ControllerUtils.responseBuilder(ControllerUtils.CODE_SUCCESS, "重置成功！");
        } catch (Exception e) {
            logger.debug("resetPassword id failed!", e);
            response = ControllerUtils.responseBuilder(ControllerUtils.CODE_ERROR, "重置失败！");
        }
        return response;
    }

    /**
     * 修改密码.
     */
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changePassword(@RequestParam(required = false)String userId,
                                              @RequestParam(required = false)String oldPassword,
                                              @RequestParam(required = false)String newPassword){
        logger.debug("UserController changePassword userId : ", userId);
        Map<String, Object> response;
        try {
            //获取需要修改密码的用户
            User user = userService.getById(userId);
            //判断输入的原密码是否一致
            if(MD5Encryption.checkpassword(oldPassword,user.getPassword())){
                //修改密码
                user.setPassword(newPassword);
                //更新信息
                userService.update(user);
                response = ControllerUtils.responseBuilder(ControllerUtils.CODE_SUCCESS, "修改成功！");
            }else{
                response = ControllerUtils.responseBuilder(ControllerUtils.CODE_ERROR, "修改失败！原密码错误");
            }
        } catch (Exception e) {
            logger.debug("changePassword userId failed!", e);
            response = ControllerUtils.responseBuilder(ControllerUtils.CODE_ERROR, "修改失败！");
        }
        return response;
    }
}
