package com.zsd.attachment.controller;

import com.zsd.attachment.domain.Attachment;
import com.zsd.attachment.service.AttachmentService;
import com.zsd.comm.orm.Page;
import com.zsd.comm.utils.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xierh
 * 附件控制器.
 */
@Controller
@RequestMapping(value = "attachment")
public class AttachmentController {

    private static Logger logger = LoggerFactory.getLogger(DownloadController.class);

    private JsonMapper jsonMapper = new JsonMapper();

    @Autowired
    private AttachmentService attachmentService;

    /**
     * 根据业务ID和类型获取附件列表.
     * @param objectId 业务ID
     * @param type 业务对象类型 1:矛盾附件,2:台账附件,3:综治培训附件
     * @return
     */
    @RequestMapping(value = "getlist", method = RequestMethod.GET, produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String getList(String objectId, Integer type) {
        try {
            List<Attachment> attachmentList = attachmentService.getList(objectId, type);
            String ret = jsonMapper.toJson(attachmentList);
            return ret;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 根据条件获取附件分页列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String search(@RequestParam(required = false) Integer pageNo,
                         @RequestParam(required = false) Integer pageSize,
                         @RequestParam(required = false) String objectId,
                         @RequestParam(required = false) int type) {
        Page<Attachment> page = new Page<>();
        if (pageNo != null && pageSize != null) {
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("objectId", objectId);
        param.put("type", type);
        page = attachmentService.search(page, param);
        return jsonMapper.toJson(page);
    }

    /**
     * 删除
     * @param id 附件ID
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String ,Object> delete(String id){
        logger.debug("delete id : {}", id);
        try {
            String[] ids = id.split(",") ;
            if (ids.length>0){
                for(int i = 0 ; i < ids.length ; i++){
                    attachmentService.delete(ids[i]);
                }
            }
            Map<String ,Object> response = new HashMap<String, Object>();
            response.put("code", "200");
            response.put("msg", "删除成功！");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String ,Object> response = new HashMap<String, Object>();
            response.put("code", "500");
            response.put("msg", "删除失败！");
            return response;
        }
    }

}
