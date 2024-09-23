import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ${PACKAGE_NAME}.dto.${NAME}DtoRequest;
import ${PACKAGE_NAME}.dto.${NAME}DtoResponse;
import ${PACKAGE_NAME}.service.${NAME}Service;

import java.util.List;

@Controller
@RequestMapping("/${NAME.replaceAll("([a-z])([A-Z])", "$1-$2").toLowerCase()}")
public class ${NAME}Controller {
    private final ${NAME}Service service;

    public ${NAME}Controller(${NAME}Service service) {
        this.service = service;
    }

    @RequestMapping({"", "/"})
    public String list(@RequestParam(value = "name", required = false) String name, Model model) {
        List<${NAME}DtoResponse> list = (name == null) ? service.findAllDto() : service.findAllDtoByName(name);
        model.addAttribute("list${NAME}", list);
        return "${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}/list";
    }

    @RequestMapping({"/{id}", "/{id}/"})
    public String details(@PathVariable("id") Long id, Model model) {
        model.addAttribute("${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}", service.findDtoById(id));
        return "${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}/details";
    }

    @RequestMapping({"/add", "/add/"})
    public String add(Model model) {
        model.addAttribute("${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}", ${NAME}DtoRequest.empty());
        model.addAttribute("action", "Add");
        return "${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}/form";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPOST(@Valid @ModelAttribute("${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}") ${NAME}DtoRequest ${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}DtoRequest,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("action", "Add");
            return "${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}/form";
        }

        ${NAME}DtoResponse ${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}DtoResponse = service.create(${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}DtoRequest);
        return String.format("redirect:/${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}/%s", ${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}DtoResponse.id());
    }

    @RequestMapping({"/{id}/edit", "/{id}/edit/"})
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}", service.findDtoById(id));
        model.addAttribute("action", "Edit");
        return "${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}/form";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String editPOST(@PathVariable("id") Long id,
                           @Valid @ModelAttribute("${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}") ${NAME}DtoRequest ${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}DtoRequest,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("action", "Edit");
            return "${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}/form";
        }

        service.update(id, ${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}DtoRequest);
        return String.format("redirect:/${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}/%s", id);
    }

    @RequestMapping({"/{id}/remove", "/{id}/remove/"})
    public String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/${NAME.substring(0, 1).toLowerCase()}${NAME.substring(1)}";
    }
}