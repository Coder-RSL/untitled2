package com.ruoyi.sidebarTree.mapper;

import com.ruoyi.sidebarTree.domain.TreePicture;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2022-07-03
 */
public interface TreePictureMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param pictureId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TreePicture selectTreePictureByPictureId(Long pictureId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param treePicture 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TreePicture> selectTreePictureList(TreePicture treePicture);

    /**
     * 新增【请填写功能名称】
     *
     * @param treePicture 【请填写功能名称】
     * @return 结果
     */
    public int insertTreePicture(TreePicture treePicture);

    /**
     * 修改【请填写功能名称】
     *
     * @param treePicture 【请填写功能名称】
     * @return 结果
     */
    public int updateTreePicture(TreePicture treePicture);

    /**
     * 删除【请填写功能名称】
     *
     * @param pictureId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTreePictureByPictureId(Long pictureId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param pictureIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTreePictureByPictureIds(Long[] pictureIds);

    public List<TreePicture> selectTreeByTreeId(int treeId);
}
