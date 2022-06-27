package com.ruoyi.sidebarTree.domain;

import com.ruoyi.common.core.domain.entity.SysMenu;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 侧边栏树的对象 sidebar_tree
 *
 * @author ruoyi
 * @date 2022-07-01
 */
public class SidebarTree extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 侧边栏树的id
     */
    private Long treeId;

    /**
     * 侧边栏树的名称
     */
    @Excel(name = "侧边栏树的名称")
    private String treeName;

    /**
     * 侧边栏树的父节点id
     */
    @Excel(name = "侧边栏树的父节点id")
    private Long parentId;

    /**
     * 是否显示，1为显示，0为不显示
     */
    @Excel(name = "是否显示，1为显示，0为不显示")
    private Integer isShow;

    /**
     * 子菜单
     */
    private List<SidebarTree> children = new ArrayList<SidebarTree>();

    /** 是哪一个树，1为种苗品种，2为种苗图片，盘穴检测和叶片检测，3为种苗表型信息和生长预测 */
    @Excel(name = "是哪一个树，1为种苗品种，2为种苗图片，盘穴检测和叶片检测，3为种苗表型信息和生长预测")
    private Integer treeType;

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setTreeType(Integer treeType) { this.treeType = treeType; }

    public Integer getTreeType() { return treeType; }

    public List<SidebarTree> getChildren() {
        return children;
    }

    public void setChildren(List<SidebarTree> children) {
        this.children = children;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("treeId", getTreeId())
                .append("treeName", getTreeName())
                .append("parentId", getParentId())
                .append("isShow", getIsShow())
                .append("treeType", getTreeType())
                .toString();
    }
}
