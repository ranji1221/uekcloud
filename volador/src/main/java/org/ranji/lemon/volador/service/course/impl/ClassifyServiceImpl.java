package org.ranji.lemon.volador.service.course.impl;

import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.pagination.PagerModel;
import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Classify;
import org.ranji.lemon.volador.service.course.prototype.IClassifyService;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("VoladorClassifyServiceImpl")
public class ClassifyServiceImpl extends GenericServiceImpl<Classify, Integer> implements IClassifyService {
    @Autowired
    private IClassifyService  classifyService;
    
}
