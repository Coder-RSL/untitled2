package com.ruoyi.sidebarTree.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.sidebarTree.domain.SidebarTree;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.sidebarTree.service.ISidebarTreeService;

/**
 * 侧边栏树的Controller
 *
 * @author ruoyi
 * @date 2022-07-01
 */
@RestController
@RequestMapping("/system/tree")
public class SidebarTreeController extends BaseController
{
    @Autowired
    private ISidebarTreeService sidebarTreeService;

    /**
     * 查询侧边栏树的列表
     */
    @GetMapping("/list")
    public AjaxResult list(SidebarTree sidebarTree)
    {
        Long userId = getUserId();
        SidebarTree sidebarTree1;
        if(userId!=null&&userId==1){
            sidebarTree1 = sidebarTreeService.selectAllTreeNode(true,sidebarTree);
        }
        else{
            sidebarTree1 = sidebarTreeService.selectAllTreeNode(false,sidebarTree);
        }
        return AjaxResult.success(sidebarTree1);
    }

    @GetMapping("/listForUserKind")//展示品种给普通用户
    public AjaxResult listForUserKind()
    {
        SidebarTree sidebarTree =new SidebarTree();
        sidebarTree.setTreeId(1L);
        SidebarTree sidebarTree1 = sidebarTreeService.selectAllTreeNode(false,sidebarTree);

        return AjaxResult.success(sidebarTree1);
    }

    /**
     * 导出侧边栏树的列表
     */
    @PreAuthorize("@ss.hasPermi('system:tree:export')")
    @Log(title = "侧边栏树的", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SidebarTree sidebarTree)
    {
        List<SidebarTree> list = sidebarTreeService.selectSidebarTreeList(sidebarTree);
        ExcelUtil<SidebarTree> util = new ExcelUtil<SidebarTree>(SidebarTree.class);
        util.exportExcel(response, list, "侧边栏树的数据");
    }

    /**
     * 获取侧边栏树的详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:tree:query')")
    @GetMapping(value = "/{treeId}")
    public AjaxResult getInfo(@PathVariable("treeId") Long treeId)
    {
        return AjaxResult.success(sidebarTreeService.selectSidebarTreeByTreeId(treeId));
    }

    /**
     * 新增侧边栏树的
     */
    @PreAuthorize("@ss.hasPermi('system:tree:add')")
    @Log(title = "侧边栏树的", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SidebarTree sidebarTree)
    {
        return toAjax(sidebarTreeService.insertSidebarTree(sidebarTree));
    }

    /**
     * 修改侧边栏树的
     */
    @PreAuthorize("@ss.hasPermi('system:tree:edit')")
    @Log(title = "侧边栏树的", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SidebarTree sidebarTree)
    {
        Long treeId = sidebarTree.getTreeId();
        if(treeId==1||treeId==2 )return AjaxResult.error("根目录不可修改");
        int i = sidebarTreeService.updateSidebarTree(sidebarTree);
        if(i==-1) return AjaxResult.error("修改失败，无修改权限");
        return toAjax(i);
    }

    /**
     * 删除侧边栏树的
     */
    @PreAuthorize("@ss.hasPermi('system:tree:remove')")
    @Log(title = "侧边栏树的", businessType = BusinessType.DELETE)
	@DeleteMapping("/{treeIds}")
    public AjaxResult remove(@PathVariable Long[] treeIds)
    {
        return toAjax(sidebarTreeService.deleteSidebarTreeByTreeIds(treeIds));
    }
}
