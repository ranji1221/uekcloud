package org.ranji.lemon.volador.service.course.prototype;

import java.util.List;

import org.ranji.lemon.core.pagination.PagerModel;
import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.course.Teacher;

/**
 * @author sertion
 * @date 2018/5/10
 * @verison 1.0
 * @since JDK1.8
 */
public interface ITeacherService extends IGenericService<Teacher,Integer> {
	/**
	 * 保存首页推荐老师
	 * @param teacher_id
	 */
	public void saveHeaderTeacher(int teacher_id);
	/**
	 * 查询首页推荐老师
	 * @return
	 */
	public List<Teacher> findHeaderTeacher();
	
	/**
	 * 删除首页推荐老师
	 * @param teacherId
	 */
	public void deleteHeaderTeacher(Integer teacherId);
	
	public PagerModel<Teacher> findTeacherInfoByPage(int page,int limit);
	
	/**
	 * 保存职业导航老师
	 * @param growthclass_id		职业导航ID
	 * @param teacher_id			老师ID
	 */
	public void saveGrowthClassTeacher(int growthclass_id, int teacher_id);
	
	/**
	 * 删除职业导航老师
	 * @param growthclass_id		职业导航ID
	 * @param teacher_id			老师ID
	 */
	public void deleteGrowthClassTeacher(int growthclass_id, int teacher_id);
	
	/**
	 * 根据职业导航ID查找老师
	 * @param growthclass_id		职业导航ID
	 * @return
	 */
	public List<Teacher> findTeacherByGrowthClassId(int growthclass_id);
}
