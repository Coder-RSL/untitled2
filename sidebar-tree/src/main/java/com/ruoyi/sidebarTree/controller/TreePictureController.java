package com.ruoyi.sidebarTree.controller;

import java.util.List;

import com.ruoyi.sidebarTree.domain.TreePicture;
import com.ruoyi.sidebarTree.service.FillService;
import com.ruoyi.sidebarTree.service.ITreePictureService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2022-07-03
 */
@RestController
@RequestMapping("/system/picture")
public class TreePictureController extends BaseController
{
    @Autowired
    private ITreePictureService treePictureService;
    @Autowired
    private FillService fillService;

    /**
     * 根据树的节点返回对应的图片url
     */

    @GetMapping("/list")
    public List<TreePicture> list(int treeId)
    {
        return treePictureService.getTreeByTreeId(treeId);

    }

    /**
     * 上传图片
     */
    @PostMapping("/upload")
    public AjaxResult upload(int treeId,@RequestParam("file") MultipartFile file,int isShow){
        boolean upload = fillService.upload(treeId, file, isShow);
        if(upload) return AjaxResult.success();//查询成功
        else return AjaxResult.error();
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:picture:query')")
    @GetMapping(value = "/{pictureId}")
    public AjaxResult getInfo(@PathVariable("pictureId") Long pictureId)
    {
        return AjaxResult.success(treePictureService.selectTreePictureByPictureId(pictureId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:picture:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TreePicture treePicture)
    {
        return toAjax(treePictureService.insertTreePicture(treePicture));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:picture:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TreePicture treePicture)
    {
        return toAjax(treePictureService.updateTreePicture(treePicture));
    }

    /**
     * 删除【请填写功能名称】
     */
    @GetMapping("/remove")
    public AjaxResult remove(int pictureId)
    {
        return toAjax(treePictureService.deleteTreePictureByPictureId((long)pictureId));
    }
}
