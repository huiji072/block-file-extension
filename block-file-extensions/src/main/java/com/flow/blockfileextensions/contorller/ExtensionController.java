package com.flow.blockfileextensions.contorller;

import com.flow.blockfileextensions.dto.ExtensionView;
import com.flow.blockfileextensions.dto.PostExtensionDto;
import com.flow.blockfileextensions.dto.PutExtensionDto;
import com.flow.blockfileextensions.entity.Extensions;
import com.flow.blockfileextensions.error.handler.CustomException;
import com.flow.blockfileextensions.error.handler.ErrorCode;
import com.flow.blockfileextensions.service.ExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ExtensionController {

    private final ExtensionService extensionService;

    @PutMapping("/pin-extensions")
    public String updatePinExtension(@Valid PutExtensionDto putExtensionDto,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomException(ErrorCode.INVALID_PARAMETER);
        }
        extensionService.updatePinExtension(putExtensionDto);
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/custom-extensions")
    public ResponseEntity<ExtensionView> saveCustomExtension(@Valid PostExtensionDto postExtensionDto,
                                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new CustomException(ErrorCode.INVALID_INPUT_LENGTH);
        }
        return new ResponseEntity(extensionService.saveCustomExtension(postExtensionDto), HttpStatus.CREATED);
    }

    @ResponseBody
    @DeleteMapping("/custom-extension/{id}")
    public Long deleteCustomExtension(@PathVariable Long id) {
        return extensionService.deleteCustomExtension(id);
    }

//    @ResponseBody
//    @DeleteMapping("/custom-extension/{id}")
//    public ResponseEntity<HttpStatus> deleteCustomExtension(@PathVariable Long id) {
//         extensionService.deleteCustomExtension(id);
//         return ResponseEntity.ok().build();
//    }

}
