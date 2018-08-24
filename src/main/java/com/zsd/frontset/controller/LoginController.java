package com.zsd.frontset.controller;

import com.zsd.comm.context.SecurityContextHolder;
import com.zsd.comm.utils.JsonMapper;
import com.zsd.frontset.service.LoginService;
import com.zsd.function.domain.Function;
import com.zsd.function.service.FunctionService;
import com.zsd.user.domain.User;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录控制器.
 * @author zhousd
 */
@Controller
@RequestMapping("")
public class LoginController {

    private JsonMapper jsonMapper = new JsonMapper();

    @Autowired
    private LoginService loginService;

    @Autowired
    private FunctionService functionService ;

    /**
     * 跳转到登录页面.
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        User user = SecurityContextHolder.getUser();
        if (ObjectUtils.notEqual(user, null)) {
            return "redirect:/index";
        }
        return "frontset/login";
    }

    /**
     * 提交登录表单.
     */
    @RequestMapping(value = "index")
    public String index(Model model, String loginName,String password) {

        User user = SecurityContextHolder.getUser();
        if (ObjectUtils.equals(user, null)) {
            if (StringUtils.isBlank(loginName) && StringUtils.isBlank(password)) {
                return "redirect:/login";
            }
            User u = new User(loginName, password);
            String error = loginService.login(u);
            if (StringUtils.isNotBlank(error)) {
                model.addAttribute("error", error);
                return "frontset/login";
            }
        }

        user = SecurityContextHolder.getUser();
        StringBuffer result = new StringBuffer("");
        List<Function> list = functionService.getByUserId(user.getId(),"root");

        Map<String, String >  map = new HashMap<>() ;

        map.put("用户管理", "fs_csview_naviimgs3");
        map.put("系统管理", "fs_csview_naviimgs10");
        map.put("作品管理", "fs_csview_naviimgs6");


        List<Map<String, String>> bannerMenuList = new ArrayList<>();

        for(int i=0;i<list.size();i++){
            Map<String, String >  map1 = new HashMap<>() ;
            map1.put("menuName",list.get(i).getName()) ;
            map1.put("menuCode" ,list.get(i).getId()) ;
            bannerMenuList.add(map1);
        }

        for (int i=bannerMenuList.size()-1 ; i>=0 ; i--) {
            Map<String, String> menuMap = bannerMenuList.get(i);
            String menuName = menuMap.get("menuName") == null ? ""
                    : menuMap.get("menuName").toString();
            String menuCode = menuMap.get("menuCode") == null ? ""
                    : menuMap.get("menuCode").toString();
            result.append(
                    "<div class='addDiv' flage='"+menuCode+"' style='width:70px;height:70px;float:right;'><a href=\"javascript:void(0);\" onclick=\"selectMenu('")
                    .append(menuCode).append("',this);\"><div class='ui-state-enabled'><div class='fs_navigation_item ' onmouseover='divOver(this);' onmouseout='divOut(this);'><img src=''class='fs_navigation_icon "+map.get(menuName)+"' style='border:0px;'><div class='fs_navigation_text'>")
                    .append(menuName).append("</div></div></div></a></div>");
        }
        model.addAttribute("bannerMenu",result);
        if(list.size() > 0){
            Map<String, Object > map2 =  functionService.getMapByUserId(user.getId(),list);
            model.addAttribute("menus",jsonMapper.toJson(map2));
        }

        return "frontset/index";
    }

    /**
     * 登出.
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        loginService.logout();
        return "redirect:/login";
    }

    @RequestMapping(value = "home")
    public String home() {
        return "frontset/home";
    }

    @RequestMapping(value = "todo")
    public String todo() {
        return "error/todo";
    }
}
