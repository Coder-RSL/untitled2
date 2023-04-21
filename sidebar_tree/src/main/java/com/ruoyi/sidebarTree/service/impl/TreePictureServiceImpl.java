package com.ruoyi.sidebarTree.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.sidebarTree.domain.TreePicture;
import com.ruoyi.sidebarTree.mapper.TreePictureMapper;
import com.ruoyi.sidebarTree.service.FillService;
import com.ruoyi.sidebarTree.service.ITreePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ruoyi.common.utils.SecurityUtils.getLoginUser;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-07-03
 */
@Service
public class TreePictureServiceImpl implements ITreePictureService
{
    @Autowired
    private TreePictureMapper treePictureMapper;
    @Autowired
    private FillService fillService;

    /**
     * 查询【请填写功能名称】
     *
     * @param pictureId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TreePicture selectTreePictureByPictureId(Long pictureId)
    {
        return treePictureMapper.selectTreePictureByPictureId(pictureId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param treePicture 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TreePicture> selectTreePictureList(TreePicture treePicture)
    {
        return treePictureMapper.selectTreePictureList(treePicture);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param treePicture 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTreePicture(TreePicture treePicture)
    {
        return treePictureMapper.insertTreePicture(treePicture);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param treePicture 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTreePicture(TreePicture treePicture)
    {
        return treePictureMapper.updateTreePicture(treePicture);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param pictureIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTreePictureByPictureIds(Long[] pictureIds)
    {
        return treePictureMapper.deleteTreePictureByPictureIds(pictureIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param pictureId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTreePictureByPictureId(Long pictureId)
    {
        TreePicture treePicture = treePictureMapper.selectTreePictureByPictureId(pictureId);

        fillService.deleteImage(treePicture.getPictureUrl());
        fillService.deleteImage(treePicture.getLessPictureUrl());
        return treePictureMapper.deleteTreePictureByPictureId(pictureId);
    }

    @Override
    public List<TreePicture> getTreeByTreeId(int treeId) {
        try {
            LoginUser loginUser = getLoginUser();
        }catch (Exception e){}

        return treePictureMapper.selectTreeByTreeId(treeId);
    }
}
