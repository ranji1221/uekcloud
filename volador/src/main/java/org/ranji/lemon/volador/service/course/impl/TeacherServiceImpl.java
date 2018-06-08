package org.ranji.lemon.volador.service.course.impl;

import java.util.List;

import org.ranji.lemon.core.pagination.PagerModel;
import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.persist.course.prototype.ITeacherDao;
import org.ranji.lemon.volador.service.course.prototype.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sertion
 * @date 2018/5/10
 * @verison 1.0
 * @since JDK1.8
 */
@Service("VoladorTeacherServiceImpl")
public class TeacherServiceImpl extends GenericServiceImpl<Teacher,Integer> implements ITeacherService {
    @Autowired
    ITeacherService teacherService;

	@Override
	public void saveHeaderTeacher(int teacher_id) {
		((ITeacherDao) dao).saveHeaderTeacher(teacher_id);
		
	}

	@Override
	public List<Teacher> findHeaderTeacher() {
		
		return ((ITeacherDao) dao).findHeaderTeacher();
	}
	
	@Override
	public PagerModel<Teacher> findTeacherInfoByPage(int page, int limit) {
		// TODO Auto-generated method stub
		
		PagerModel<Teacher> pagerModel =new PagerModel<Teacher>();
		pagerModel.setData(((ITeacherDao) dao).findTeacherInfoByPage(page, limit));
		pagerModel.setTotal(((ITeacherDao) dao).findTeacherCount());
		return pagerModel;
	}

	@Override
	public void saveGrowthClassTeacher(int growthclass_id, int teacher_id) {
		((ITeacherDao) dao).saveGrowthClassTeacher(growthclass_id, teacher_id);		
	}

	@Override
	public void deleteGrowthClassTeacher(int growthclass_id, int teacher_id) {
		((ITeacherDao) dao).deleteGrowthClassTeacher(growthclass_id, teacher_id);
	}

	@Override
	public List<Teacher> findTeacherByGrowthClassId(int growthclass_id) {
		return ((ITeacherDao) dao).findTeacherByGrowthClassId(growthclass_id);
	}

	@Override
	public void deleteHeaderTeacher(Integer teacherId) {
		((ITeacherDao) dao).deleteHeaderTeacher(teacherId);
		
	}
	
}
