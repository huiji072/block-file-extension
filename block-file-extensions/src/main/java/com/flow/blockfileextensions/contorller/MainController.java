package com.flow.blockfileextensions.contorller;
import com.flow.blockfileextensions.constant.ExtensionType;
import com.flow.blockfileextensions.service.ExtensionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final ExtensionService extensionService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("customExtensionsList", extensionService.getExtensionList(ExtensionType.CUSTOM));
        model.addAttribute("customExtensionsListSize", extensionService.getCustomExtensionCount());
        model.addAttribute("pinExtensionsList", extensionService.getExtensionList(ExtensionType.PIN));
        return "home";
    }
}
