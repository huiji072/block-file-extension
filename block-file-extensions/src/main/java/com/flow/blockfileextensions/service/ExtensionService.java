package com.flow.blockfileextensions.service;

import com.flow.blockfileextensions.constant.ExtensionType;
import com.flow.blockfileextensions.dto.PostExtensionDto;
import com.flow.blockfileextensions.dto.PutExtensionDto;
import com.flow.blockfileextensions.entity.Extensions;
import com.flow.blockfileextensions.error.handler.CustomException;
import com.flow.blockfileextensions.error.handler.ErrorCode;
import com.flow.blockfileextensions.repository.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExtensionService {
    private final ExtensionRepository extensionRepository;

    @Transactional
    public void updatePinExtension(PutExtensionDto putExtensionDto) {
        Extensions extensions = extensionRepository.findByName(putExtensionDto.getName())
                .orElseThrow(() -> new RuntimeException("해당 확장자가 존재하지 않습니다."));
        extensions.setBlocked(!extensions.getBlocked());
    }

    @Transactional
    public Extensions saveCustomExtension(PostExtensionDto postExtensionDto) {
        validateDuplicateExtensions(postExtensionDto);
        customExtensionCountWithLimit();
        Extensions extension = new Extensions();
        extension.setName(postExtensionDto.getName());
        extension.setExtensionType(ExtensionType.CUSTOM);
        extension.setBlocked(false);
        return extensionRepository.save(extension);
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

    public Long deleteCustomExtension(Long id) {
        extensionRepository.deleteById(id);
        return id;
    }

    private void validateDuplicateExtensions(PostExtensionDto extensions) {
        extensionRepository.findByName(extensions.getName())
                .ifPresent(extensionName -> {
                    throw new CustomException(ErrorCode.ALREADY_SAVED_EXTENSION);
                });
    }

    public void customExtensionCountWithLimit() {
        Long customExtensionSize = extensionRepository.countByExtensionType(ExtensionType.CUSTOM);
        if (customExtensionSize >= 200) {
            throw new CustomException(ErrorCode.INVALID_EXTENSION_COUNT);
        }
    }
}
