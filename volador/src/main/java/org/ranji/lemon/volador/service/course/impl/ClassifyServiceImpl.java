package org.ranji.lemon.volador.service.course.impl;

import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.pagination.PagerModel;
import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Classify;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.persist.course.prototype.IClassifyDao;
import org.ranji.lemon.volador.persist.course.prototype.ICourseDao;
import org.ranji.lemon.volador.service.course.prototype.IClassifyService;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("VoladorClassifyServiceImpl")
public class ClassifyServiceImpl extends GenericServiceImpl<Classify, Integer> implements IClassifyService {
	
    @Autowired
    private IClassifyService  classifyService;

	@Override
	public void saveClassifyAndCourseRelation(int course_id, int classify_id) {
		 ((IClassifyDao) dao).saveClassifyAndCourseRelation(course_id, classify_id);
	}

	@Override
	public void deleteClassifyAndCourseRelation(int course_id, int classify_id) {
		((IClassifyDao) dao).deleteClassifyAndCourseRelation(course_id, classify_id);
	}

	@Override
	public List<Course> findCourseByClassify(int classify_id) {
		return ((IClassifyDao) dao).findCoursebyClassify(classify_id);
	}

	@Override
	public Classify findClassifyByCourseId(Integer course_id) {
		if(null != course_id){
			List<Classify> classifyList = ((IClassifyDao) dao).findClassifyByCourseId(course_id);
			if(0 != classifyList.size()){
				return classifyList.get(0);
			}else{
				return null;
			}
			
		}else{
			return null;
		}
	}
}
