package com.managersystem.controller;

import com.managersystem.model.dto.DeveloperDto;
import com.managersystem.model.dto.ProjectDto;
import com.managersystem.service.impl.ProjectService;
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

@WebServlet(urlPatterns = "/project")
public class ProjectController extends HttpServlet {
    private ProjectService projectService;

    @Override
    public void init(ServletConfig config) {
        projectService = (ProjectService) config.getServletContext().getAttribute(Attribute.PROJECT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProjectDto> projects = projectService.findAll();
        req.setAttribute(Attribute.PROJECTS, projects);
        UtilController.forwardToPage(req, resp, Page.PROJECTS);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectDto projectDto = mapProjectDto(req);
        String deleteOrNot = req.getParameter(Parameter.DELETE);
        String createOrNot = req.getParameter(Parameter.CREATE);
        if (Objects.nonNull(deleteOrNot)) {
            projectService.delete(projectDto);
        } else if (Objects.nonNull(createOrNot)) {
            projectService.save(mapProjectDto(req));
        } else {
            projectService.update(projectDto);
        }

        doGet(req, resp);
    }

    private ProjectDto mapProjectDto(HttpServletRequest request) {
        ProjectDto projectDto = new ProjectDto();
        String parameterId = request.getParameter(Parameter.ID);
        if (Objects.nonNull(parameterId)) {
            projectDto.setId(Integer.parseInt(parameterId));
        }
        projectDto.setName(request.getParameter(Parameter.NAME));
        projectDto.setComplexity(request.getParameter(Parameter.COMPLEXITY));
        projectDto.setCost(Integer.parseInt(request.getParameter(Parameter.COST)));
        return projectDto;
    }
}
