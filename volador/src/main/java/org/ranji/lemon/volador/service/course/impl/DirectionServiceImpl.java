package org.ranji.lemon.volador.service.course.impl;

import java.util.ArrayList;
import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Classify;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Direction;
import org.ranji.lemon.volador.persist.course.prototype.IDirectionDao;
import org.ranji.lemon.volador.service.course.prototype.IClassifyService;
import org.ranji.lemon.volador.service.course.prototype.IDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 课程方向service实现类
 * @author 范小亚
 * @date 2018/5/28
 * @since JDK1.8
 * @version 1.0
 */
@Service("VoladorCourseDirectionServiceImpl")
public class DirectionServiceImpl extends GenericServiceImpl<Direction, Integer> implements IDirectionService{
	
	@Autowired
	private IClassifyService classifyService;

	@Override
	public void saveDirectionAndClassifyRelation(int directionId, int classifyId) {
		((IDirectionDao) dao).saveDirectionAndClassifyRelation(directionId, classifyId);
	}

	@Override
	public void deleteDirectionAndClassifyRelation(int directionId, int classifyId) {
		((IDirectionDao) dao).deleteDirectionAndClassifyRelation(directionId, classifyId);
	}

	@Override
	public void deleteDirectionAndClassifyRelationByDirectionId(int directionId) {
		((IDirectionDao) dao).deleteDirectionAndClassifyRelationByDirectionId(directionId);
	}

	@Override
	public List<Classify> findClassifyByDirectionId(int directionId) {
		return ((IDirectionDao) dao).findClassifyByDirectionId(directionId);
	}

	@Override
	public List<Course> findCourseByDirectionId(int directionId) {
		//获取课程分类列表
		List<Classify> classifyList = findClassifyByDirectionId(directionId);
		//定义返回的课程列表
		List<Course> courseList = new ArrayList<>();
		
		for(Classify classify:classifyList){
			courseList.addAll(classifyService.findCourseByClassify(classify.getId()));
		}		
		return courseList;
	}

	@Override
	public Integer findDirectionIdByClassiyId(int classifyId) {
		List<Integer> directionList = ((IDirectionDao) dao).findDirectionIdByClassiyId(classifyId);
		return directionList.get(0);
	}
}
