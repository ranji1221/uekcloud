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
	
	public PagerModel<Teacher> findTeacherInfoByPage(int page,int limit);
}
