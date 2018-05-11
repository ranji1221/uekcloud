package org.ranji.lemon.volador.persist.course.impl;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Accessory;
import org.ranji.lemon.volador.persist.course.prototype.IAccessoryDao;
import org.springframework.stereotype.Repository;

/**
 * @author sertion
 * @date 2018/5/10
 * @verison 1.0
 * @since JDK1.8
 */
@Repository("VoladorAccessoryDaoImpl")
public class AccessoryDaoImpl extends GenericDaoImpl<Accessory,Integer> implements IAccessoryDao {
}
