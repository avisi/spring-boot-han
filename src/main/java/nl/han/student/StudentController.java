package nl.han.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/")
    public ModelAndView home(final ModelMap model) {
        defaultModel(model);
        model.addAttribute("studentForm", new StudentForm());
        return new ModelAndView("home", model);
    }

    @PostMapping("/add")
    public ModelAndView add(@ModelAttribute @Valid final StudentForm student, final BindingResult result, final ModelMap model) {
        if (result.hasErrors()) {
            defaultModel(model);
            model.mergeAttributes(result.getModel());
            model.addAttribute("studentForm", student);
            return new ModelAndView("home", model);
        }
        studentService.save(new Student(Integer.parseInt(student.getNummer()), student.getNaam()));
        return new ModelAndView("redirect:/", model);
    }

    private void defaultModel(ModelMap model) {
        model.addAttribute("name", "Students");
        model.addAttribute("students", studentService.all());
    }

}