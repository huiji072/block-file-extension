package com.flow.blockfileextensions.contorller;

import com.flow.blockfileextensions.dto.ExtensionView;
import com.flow.blockfileextensions.dto.CreateExtensionDto;
import com.flow.blockfileextensions.dto.UpdateExtensionDto;
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
    public String updatePinExtension(@Valid UpdateExtensionDto updateExtensionDto,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomException(ErrorCode.INVALID_PARAMETER);
        }
        extensionService.updatePinExtension(updateExtensionDto);
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/custom-extensions")
    public ResponseEntity<ExtensionView> saveCustomExtension(@Valid CreateExtensionDto createExtensionDto,
                                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new CustomException(ErrorCode.INVALID_INPUT_LENGTH);
        }
        return new ResponseEntity(extensionService.saveCustomExtension(createExtensionDto), HttpStatus.CREATED);
    }

    @ResponseBody
    @DeleteMapping("/custom-extension/{id}")
    public ResponseEntity<HttpStatus> deleteCustomExtension(@PathVariable Long id) {
         extensionService.deleteCustomExtension(id);
         return ResponseEntity.ok().build();
    }

}
