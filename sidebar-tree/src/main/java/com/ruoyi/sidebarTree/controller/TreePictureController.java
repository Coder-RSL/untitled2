package com.ruoyi.sidebarTree.controller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ruoyi.sidebarTree.domain.TreePicture;
import com.ruoyi.sidebarTree.service.FillService;
import com.ruoyi.sidebarTree.service.ITreePictureService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.client.RestTemplate;
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
        try {
            Long userId = getUserId();
        }catch (Exception e){
        }
        return treePictureService.getTreeByTreeId(treeId);

    }

    /**
     * 上传图片
     */
    @PostMapping("/upload")
    public AjaxResult upload(int treeId,@RequestParam("file") MultipartFile file,int isShow){
        int res= fillService.upload(treeId, file, isShow);

        if(res==1){
            return AjaxResult.success("图片上传成功");
        }
        else if(res==0){
            return AjaxResult.error("图片上传失败");
        }
        else if(res==2){
            AjaxResult result =new AjaxResult(501,"部分图片上传成功，但存在不兼容文件，请检查文件类型与格式");
            return result;
        }
        return AjaxResult.error();
    }

    @PostMapping("/uploadFromIp")
    public AjaxResult uploadFromIp(int treeId,@RequestParam("file") MultipartFile file){
        int isShow=1;
        int res= fillService.upload(treeId, file, isShow);

        if(res==1){
            return AjaxResult.success("图片上传成功");
        }
        else if(res==0){
            return AjaxResult.error("图片上传失败");
        }
        else if(res==2){
            AjaxResult result =new AjaxResult(501,"部分图片上传成功，但存在不兼容文件，请检查文件类型与格式");
            return result;
        }
        return AjaxResult.error();
    }


    @PostMapping("/uploadByIp")
    public AjaxResult uploadByIp(String ip,String parentFile,int treeId){
        try {
            // 定义要发送的字符
            String text = parentFile + "&&&" + treeId;

            // 创建HttpHeaders对象，设置Content-Type为text/plain
            HttpHeaders headers = new HttpHeaders();
            // headers.setContentType(MediaType.TEXT_PLAIN);
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
            // 创建HttpEntity对象，将字符设置为请求体，并将HttpHeaders对象传递给构造函数
            HttpEntity<String> request = new HttpEntity<>(text, headers);

            // 使用RestTemplate发送HTTP POST请求
            RestTemplate restTemplate = new RestTemplate();
            //flask所在
            String url = "http://" + ip + ":9999/text";
            restTemplate.postForEntity(url, request, String.class);
//            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
//
//            // 获取Flask返回的响应
//            String flaskResponse = response.getBody();
        }
        catch (Exception e){
            e.printStackTrace();
            return error("连接失败");
        }
        return success("连接成功");
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
