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

}
