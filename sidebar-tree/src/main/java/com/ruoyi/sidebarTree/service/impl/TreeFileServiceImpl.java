package com.ruoyi.sidebarTree.service.impl;

import java.util.List;

import com.ruoyi.sidebarTree.domain.TreeFile;
import com.ruoyi.sidebarTree.mapper.TreeFileMapper;
import com.ruoyi.sidebarTree.service.ITreeFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 树节点上的文件Service业务层处理
 *
 * @author ruoyi
 * @date 2022-07-04
 */
@Service
public class TreeFileServiceImpl implements ITreeFileService
{
    @Autowired
    private TreeFileMapper treeFileMapper;

    /**
     * 查询树节点上的文件
     *
     * @param fileId 树节点上的文件主键
     * @return 树节点上的文件
     */
    @Override
    public TreeFile selectTreeFileByFileId(Long fileId)
    {
        return treeFileMapper.selectTreeFileByFileId(fileId);
    }

    /**
     * 查询树节点上的文件列表
     *
     * @param treeFile 树节点上的文件
     * @return 树节点上的文件
     */
    @Override
    public List<TreeFile> selectTreeFileList(TreeFile treeFile)
    {
        return treeFileMapper.selectTreeFileList(treeFile);
    }

    /**
     * 新增树节点上的文件
     *
     * @param treeFile 树节点上的文件
     * @return 结果
     */
    @Override
    public int insertTreeFile(TreeFile treeFile)
    {
        return treeFileMapper.insertTreeFile(treeFile);
    }

    /**
     * 修改树节点上的文件
     *
     * @param treeFile 树节点上的文件
     * @return 结果
     */
    @Override
    public int updateTreeFile(TreeFile treeFile)
    {
        return treeFileMapper.updateTreeFile(treeFile);
    }

    /**
     * 批量删除树节点上的文件
     *
     * @param fileIds 需要删除的树节点上的文件主键
     * @return 结果
     */
    @Override
    public int deleteTreeFileByFileIds(Long[] fileIds)
    {
        return treeFileMapper.deleteTreeFileByFileIds(fileIds);
    }

    /**
     * 删除树节点上的文件信息
     *
     * @param fileId 树节点上的文件主键
     * @return 结果
     */
    @Override
    public int deleteTreeFileByFileId(Long fileId)
    {
        return treeFileMapper.deleteTreeFileByFileId(fileId);
    }
}
