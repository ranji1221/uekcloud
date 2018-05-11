package org.ranji.lemon.volador.service.course.impl;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Note;
import org.ranji.lemon.volador.service.course.prototype.INoteService;
import org.springframework.stereotype.Service;
/**
 * NoteService实现类
 * @author 范小亚
 * @since JDK1.8
 * @date 2018/5/11
 * @version 1.0
 *
 */
@Service("VoladorCourseNoteServiceImpl")
public class NoteServiceImpl extends GenericServiceImpl<Note, Integer> implements INoteService{

}
