package com.zsd.authority.controller;

import com.zsd.authority.domain.Role;
import com.zsd.authority.domain.RoleFunction;
import com.zsd.authority.service.RoleFunctionService;
import com.zsd.authority.service.RoleService;
import com.zsd.comm.orm.Page;
import com.zsd.comm.utils.ControllerUtils;
import com.zsd.comm.utils.JsonMapper;
import com.zsd.function.domain.TreeNode;
import com.zsd.function.service.FunctionService;
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
import java.util.List;
import java.util.Map;

/**
 * Created by msx on 2016/6/16.
 * 角色控制器.
 */
@Controller
@RequestMapping("role")
public class RoleController {
    private static Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;
    @Autowired
    private FunctionService functionService;
    @Autowired
    private RoleFunctionService roleFunctionService;

    private JsonMapper jsonMapper = new JsonMapper();

    /**
     * 显示角色页面.
     */
    @RequestMapping(value = "gotoPage", method = RequestMethod.GET)
    public ModelAndView gotoPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("authority/role/list");
        return mv;
    }

    /**
     * 分页.
     */
    @RequestMapping(value = "search", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String search(@RequestParam(required = false) String name,
                         @RequestParam(required = false) Integer pageNo,
                         @RequestParam(required = false) Integer pageSize) {
        Page<Role> page = new Page<>();
        //获取分页信息
        if (pageNo != null && pageSize != null) {
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
        }
        //查询条件
        Map<String, Object> param = new HashMap<>();
        param.put("name", name);
        //获取查询结果
        page = roleService.search(page, param);

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
            Role role = jsonMapper.fromJson(json, Role.class);
            roleService.save(role);
            response = ControllerUtils.responseBuilder(ControllerUtils.CODE_SUCCESS, "保存成功！");
            return response;
        } catch (Exception e) {
            logger.debug(e.getMessage());
            response = ControllerUtils.responseBuilder(ControllerUtils.CODE_ERROR, "保存失败！");
            return response;
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete(String idStr) {
        logger.debug("UserController delete id : {}", idStr);
        Map<String, Object> response;
        try {
            roleService.deleteIdStr(idStr);
            response = ControllerUtils.responseBuilder(ControllerUtils.CODE_SUCCESS, "删除成功！");
        } catch (Exception e) {
            logger.debug("delete idStr failed!", e);
            response = ControllerUtils.responseBuilder(ControllerUtils.CODE_ERROR, "删除失败！");
        }
        return response;
    }

    /**
     * 权限树.
     */
    @RequestMapping(value = "tree", method = RequestMethod.GET)
    @ResponseBody
    public String tree(String roleId) {
        //获取所有权限节点
        List<TreeNode> treeNodeList = functionService.getAllNode();
        //获取角色对应的权限
        List<RoleFunction> roleFunctionList = roleFunctionService.getByRoleId(roleId);
        //遍历添加
        for(RoleFunction roleFunction: roleFunctionList){
            for(TreeNode treeNode : treeNodeList){
                if(treeNode.getId().equals(roleFunction.getFunctionId())){
                    treeNode.setChecked(true);
                }
            }
        }
        String ret = jsonMapper.toJson(treeNodeList);
        return ret;
    }

    /**
     * 保存权限.
     */
    @RequestMapping(value = "saveTree", method = RequestMethod.POST)
    @ResponseBody
    public String saveTree(String roleId,String json) {
        //去除[]
        json = json.substring(1,json.length()-1);
        //去除""
        json=json.replaceAll("\"", "");

        String[] ids = json.split(",");

        //删除角色的权限
        roleFunctionService.deleteByRoleId(roleId);
        //添加角色权限
        RoleFunction roleFunction = new RoleFunction();
        for(int i = 0;i<ids.length;i++){
            roleFunction.setRoleId(roleId);
            roleFunction.setFunctionId(ids[i]);
            roleFunctionService.save(roleFunction);
        }
        return "保存成功";
    }
}
