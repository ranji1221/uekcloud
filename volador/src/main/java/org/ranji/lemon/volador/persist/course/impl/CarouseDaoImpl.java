package org.ranji.lemon.volador.persist.course.impl;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Carouse;
import org.ranji.lemon.volador.persist.course.prototype.ICarouseDao;
import org.springframework.stereotype.Repository;
/**
 * 轮播图Dao层实现类
 * @author 范小亚
 * @date 2018/6/4
 * @since JDK1.8
 * @version 1.0
 */
@Repository("VoladorCarouseDaoImpl")
public class CarouseDaoImpl extends GenericDaoImpl<Carouse, Integer> implements ICarouseDao{

}
