package com.managersystem.controller;


import com.managersystem.model.dto.SkillDto;
import com.managersystem.service.impl.SkillService;
import com.managersystem.util.constants.Attribute;
import com.managersystem.util.constants.Page;
import com.managersystem.util.constants.Parameter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = "/skill")
public class SkillController extends HttpServlet {
    private SkillService skillService;

    @Override
    public void init(ServletConfig config) {
        skillService = (SkillService) config.getServletContext().getAttribute(Attribute.SKILL_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SkillDto> skills = skillService.findAll();
        req.setAttribute(Attribute.SKILLS, skills);
        UtilController.forwardToPage(req, resp, Page.SKILL);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SkillDto skillDto = mapSkillDto(req);
        String deleteOrNot = req.getParameter(Parameter.DELETE);
        String createOrNot = req.getParameter(Parameter.CREATE);
        if (Objects.nonNull(deleteOrNot)) {
            skillService.delete(skillDto);
        } else if (Objects.nonNull(createOrNot)) {
            skillService.save(mapSkillDto(req));
        } else {
            skillService.update(skillDto);
        }

        doGet(req, resp);
    }

    private SkillDto mapSkillDto(HttpServletRequest request) {
        SkillDto skillDto = new SkillDto();
        String parameterId = request.getParameter(Parameter.ID);
        if (Objects.nonNull(parameterId)) {
            skillDto.setId(Integer.parseInt(parameterId));
        }
        skillDto.setLanguage(request.getParameter(Parameter.LANGUAGES));
        skillDto.setLevel(request.getParameter(Parameter.LEVEL));
        return skillDto;
    }
}
