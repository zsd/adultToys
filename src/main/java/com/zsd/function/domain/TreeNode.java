package com.zsd.function.domain;

import com.zsd.comm.domain.EntityImpl;

/**
 * Created by msx on 2016/6/24.
 * 节点实体
 */
public class TreeNode extends EntityImpl {
    private String pId;
    private int orderNum ;
    private boolean checked = false;
    private boolean open = true;
    public TreeNode() {
        super();
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
