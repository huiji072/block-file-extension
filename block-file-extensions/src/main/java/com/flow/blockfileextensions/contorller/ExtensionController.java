package com.flow.blockfileextensions.contorller;

import com.flow.blockfileextensions.dto.PostExtensionDto;
import com.flow.blockfileextensions.dto.PutExtensionDto;
import com.flow.blockfileextensions.entity.Extensions;
import com.flow.blockfileextensions.error.handler.CustomException;
import com.flow.blockfileextensions.error.handler.ErrorCode;
import com.flow.blockfileextensions.service.ExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ExtensionController {

    private final ExtensionService extensionService;

    @PutMapping("/pin-extensions/{id}")
    public String updatePinExtension(@PathVariable Long id,
                                     @Valid PutExtensionDto putExtensionDto,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomException(ErrorCode.INVALID_PARAMETER);
        }
        extensionService.updatePinExtension(putExtensionDto);
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/custom-extensions")
    public Extensions saveCustomExtension(@Valid PostExtensionDto postExtensionDto,
                                          BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new CustomException(ErrorCode.INVALID_INPUT_LENGTH);
        }
        return extensionService.saveCustomExtension(postExtensionDto);
    }

    @ResponseBody
    @DeleteMapping("/custom-extension/{id}")
    public Long deleteCustomExtension(@PathVariable Long id) {
        return extensionService.deleteCustomExtension(id);
    }
}
