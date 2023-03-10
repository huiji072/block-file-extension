package com.flow.blockfileextensions.service;

import com.flow.blockfileextensions.constant.ExtensionType;
import com.flow.blockfileextensions.dto.ExtensionView;
import com.flow.blockfileextensions.dto.CreateExtensionDto;
import com.flow.blockfileextensions.dto.UpdateExtensionDto;
import com.flow.blockfileextensions.entity.Extensions;
import com.flow.blockfileextensions.error.handler.CustomException;
import com.flow.blockfileextensions.error.handler.ErrorCode;
import com.flow.blockfileextensions.repository.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExtensionService {
    private final ExtensionRepository extensionRepository;
    private final int MAX_SIZE =200;
    @Transactional
    public void updatePinExtension(UpdateExtensionDto updateExtensionDto) {
        Extensions extensions = getExtensionByName(updateExtensionDto);
        validPinExtensionCheck(updateExtensionDto);
        extensions.setBlocked(!extensions.getBlocked());
    }
    private Extensions getExtensionByName(UpdateExtensionDto updateExtensionDto) {
        return extensionRepository.findByName(updateExtensionDto.getName())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 확장자입니다.")
                );
    }
    private Extensions validPinExtensionCheck(UpdateExtensionDto updateExtensionDto) {
        Extensions extensions = getExtensionByName(updateExtensionDto);
        if (extensions.getExtensionType() != ExtensionType.PIN) {
            throw new RuntimeException("고정 확장자가 아닙니다.");
        }
        return extensions;
    }

    @Transactional
    public ExtensionView saveCustomExtension(CreateExtensionDto createExtensionDto) {
        validCustomExtensionCheck(createExtensionDto);
        return ExtensionView.from(
                extensionRepository.save(createExtensionDto.toEntity())
        );
    }

    private void validCustomExtensionCheck(CreateExtensionDto extensions) {
        if (extensionRepository.countByName(extensions.getName()) > 0) {
            throw new CustomException(ErrorCode.ALREADY_SAVED_EXTENSION);
        }
        long customExtensionSize = extensionRepository.countByExtensionType(ExtensionType.CUSTOM);
        if (customExtensionSize >= MAX_SIZE) {
            throw new CustomException(ErrorCode.INVALID_EXTENSION_COUNT);
        }
    }

    public List<Extensions> getPinExtensionList() {
        return extensionRepository.findByExtensionType(ExtensionType.PIN);
    }

    public List<Extensions> getCustomExtensionList() {
        return extensionRepository.findByExtensionType(ExtensionType.CUSTOM);
    }

    public Long getCustomExtensionCount() {
        return extensionRepository.countByExtensionType(ExtensionType.CUSTOM);
    }

    @Transactional
    public void deleteCustomExtension(Long id) {
        extensionRepository.deleteById(id);
    }

    private Extensions getExtensionById(Long id) {
        return extensionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재하지 않는 확장자입니다.")
        );
    }
}
