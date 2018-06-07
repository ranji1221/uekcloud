package org.ranji.lemon.volador.persist.course.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.course.Teacher;

/**
 * @author sertion
 * @date 2018/5/9
 * @since JDK1.8
 * @version 1.0
 */
public interface ITeacherDao extends IGenericDao<Teacher,Integer> {

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
	
	public List<Teacher> findTeacherInfoByPage(int page,int limit);
	
	public int  findTeacherCount();
	
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
