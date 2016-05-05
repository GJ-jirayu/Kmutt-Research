package th.ac.kmutt.research.portlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.ac.kmutt.research.service.ResearchService;

@Controller("roleMappingController")
@RequestMapping("VIEW")
@SessionAttributes({"roleMappingForm"})
public class RoleMappingController {
    @Autowired
    @Qualifier("researchServiceWSImpl")
    private ResearchService researchService;
}
