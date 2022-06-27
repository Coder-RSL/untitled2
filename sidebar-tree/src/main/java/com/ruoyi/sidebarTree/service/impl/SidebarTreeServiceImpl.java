package com.ruoyi.sidebarTree.service.impl;

import java.util.Arrays;
import java.util.List;

import com.ruoyi.sidebarTree.domain.SidebarTree;
import com.ruoyi.sidebarTree.service.ISidebarTreeService;
import com.ruoyi.sidebarTree.mapper.SidebarTreeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 侧边栏树的Service业务层处理
 *
 * @author ruoyi
 * @date 2022-07-01
 */
@Service
public class SidebarTreeServiceImpl implements ISidebarTreeService
{
    @Autowired
    private SidebarTreeMapper sidebarTreeMapper;

    /**
     * 查询侧边栏树的
     *
     * @param treeId 侧边栏树的主键
     * @return 侧边栏树的
     */
    @Override
    public SidebarTree selectSidebarTreeByTreeId(Long treeId)
    {
        return sidebarTreeMapper.selectSidebarTreeByTreeId(treeId);
    }

    /**
     * 查询侧边栏树的列表
     *
     * @param sidebarTree 侧边栏树的
     * @return 侧边栏树的
     */
    @Override
    public List<SidebarTree> selectSidebarTreeList(SidebarTree sidebarTree)
    {
        return sidebarTreeMapper.selectSidebarTreeList(sidebarTree);
    }

    /**
     * 新增侧边栏树的
     *
     * @param sidebarTree 侧边栏树的
     * @return 结果
     */
    @Override
    public int insertSidebarTree(SidebarTree sidebarTree)
    {
        return sidebarTreeMapper.insertSidebarTree(sidebarTree);
    }

    /**
     * 修改侧边栏树的
     *
     * @param sidebarTree 侧边栏树的
     * @return 结果
     */
    @Override
    public int updateSidebarTree(SidebarTree sidebarTree)
    {
        return sidebarTreeMapper.updateSidebarTree(sidebarTree);
    }

    /**
     * 批量删除侧边栏树的
     *
     * @param treeIds 需要删除的侧边栏树的主键
     * @return 结果
     */
    @Override
    public int deleteSidebarTreeByTreeIds(Long[] treeIds)
    {
        return sidebarTreeMapper.deleteSidebarTreeByTreeIds(treeIds);
    }

    /**
     * 删除侧边栏树的信息
     *
     * @param treeId 侧边栏树的主键
     * @return 结果
     */
    @Override
    public int deleteSidebarTreeByTreeId(Long treeId)
    {
        return sidebarTreeMapper.deleteSidebarTreeByTreeId(treeId);
    }

    /**
     * 获取子节点列表
     * @param t 返回的总的SidebarTree对象，作为侧边栏树
     * @return 返回子节点列表
     */
    @Override
    public List<SidebarTree> getChildrenList(SidebarTree t) {
        List<SidebarTree> list = sidebarTreeMapper.selectTreeNodeByParentId(t.getTreeId(),t.getTreeType());
        return list;
    }

    /**
     * 查询树的第一层节点
     *
     * @param tree 传入的参数
     * @return 返回所有第一层节点
     */
    @Override
    public List<SidebarTree> selectTreeList(SidebarTree tree) {
        tree.setParentId(0L);
        System.out.println(tree);
        return sidebarTreeMapper.selectSidebarTreeList(tree);
    }

    /**
     * 查询树的所有子节点
     * @return 返回的所有节点
     */
    @Override
    public SidebarTree selectAllTreeNode(SidebarTree tree) {
        List<SidebarTree> trees = selectTreeList(tree);
        tree.setChildren(trees);
        recursionAllNodes(tree, trees,tree.getTreeType());
        return tree;
    }

    @Override
    public SidebarTree recursionAllNodes(SidebarTree tree,List<SidebarTree> nextNode,int treeType) {
        if(nextNode.size()==0){
            return null;
        }

        for (int i = 0; i < nextNode.size(); i++) {
            List<SidebarTree> trees = sidebarTreeMapper.selectTreeNodeByParentId(nextNode.get(i).getTreeId(),treeType);
            nextNode.get(i).setChildren(trees);
            recursionAllNodes(nextNode.get(i), trees,treeType);
        }
        return tree;
    }


}
