package org.ranji.lemon.volador.persist.course.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.course.Classify;
import org.ranji.lemon.volador.model.course.Direction;
/**
 * 课程方向
 * @author 范小亚
 * @date 2018/5/28
 * @since JDK1.8
 * @version 1.0
 */
public interface IDirectionDao extends IGenericDao<Direction, Integer>{
	/**
	 * 添加课程方向与课程分类关系
	 * @param directionId    课程方向ID
	 * @param classifyId     课程分类ID
	 */
	public void saveDirectionAndClassifyRelation(int directionId, int classifyId);
	
	/**
	 * 删除课程方向与课程分类关系
	 * @param directionId    课程方向ID
	 * @param classifyId     课程分类ID
	 */
	public void deleteDirectionAndClassifyRelation(int directionId, int classifyId);
	
	/**
	 * 删除某个课程方向与课程分类的关系
	 * @param directionId    课程方向ID
	 */
	public void deleteDirectionAndClassifyRelationByDirectionId(int directionId);
	
	/**
	 * 通过课程方向ID查找该课程方向下的所有分类
	 * @param directionId    课程方向ID
	 * @return
	 */
	public List<Classify> findClassifyByDirectionId(int directionId);
	
	/**
	 * 通过课程分类ID查找课程方向ID
	 * @param classifyId    课程分类ID
	 * @return
	 */
	public List<Integer> findDirectionIdByClassiyId(int classifyId);

}
