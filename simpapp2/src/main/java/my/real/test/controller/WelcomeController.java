package my.real.test.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.ServletContext;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1", method = RequestMethod.GET)
public class WelcomeController {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Autowired
    private ServletContext servletContext;

    @GetMapping(path = "/welcome")
    public ModelAndView doWelcome() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "Pleaze Wilcomen!!");
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

    @GetMapping
    public ModelAndView showAllLinks() {
        final ModelAndView modelAndView = new ModelAndView();
        final Map<RequestMappingInfo, HandlerMethod> handlerMethods = this.handlerMapping.getHandlerMethods();
        final Map<String, String> controllerPatterns = handlerMethods.entrySet().stream()
                .map(e -> e.getKey().getPatternsCondition().getPatterns())
                .flatMap(strs -> strs.stream())
                .collect(Collectors.toMap(s -> StringUtils.substringAfterLast(s, "/"),
                        s -> servletContext.getContextPath() + s));
        modelAndView.addObject("links", controllerPatterns);
        modelAndView.setViewName("showlinks");
        return modelAndView;
    }

}
