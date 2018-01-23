package com.yejunfeng.action;

import com.yejunfeng.PO.ClassesPO;
import com.yejunfeng.dao.ClassesDao;
import com.yejunfeng.exception.ClassesExistException;
import com.yejunfeng.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="classes")
public class ClassesAction {

    @Autowired
    private ClassesService classesService;

    @RequestMapping(value="toAddView")
    public String toAddView(){
        return "classes/classes-add";
    }

    @RequestMapping(value = "toAdd")
    public String toAdd(ClassesPO classesPO, Model model){
//        classesService
        try {
            classesService.addClasses(classesPO);
            model.addAttribute("result","success");
        } catch (ClassesExistException e) {
            model.addAttribute("result",e.getMessage());
        }
        return "classes/classes-add";
    }

    @RequestMapping(value="toView")
    public String toView(@RequestParam int thisPage,Model model){
        classesService.getClassesDivide(thisPage,5);

        return "classes/classes-view";
    }
}
