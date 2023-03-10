package com.flow.blockfileextensions.service;

import com.flow.blockfileextensions.constant.ExtensionType;
import com.flow.blockfileextensions.dto.ExtensionView;
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

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExtensionService {
    private final ExtensionRepository extensionRepository;

    @Transactional
    public void updatePinExtension(PutExtensionDto putExtensionDto) {
        Extensions extensions = getExtensionByName(putExtensionDto);
        validPinExtensionCheck(putExtensionDto);
        extensions.setBlocked(!extensions.getBlocked());
    }
    private Extensions getExtensionByName(PutExtensionDto putExtensionDto) {
        return extensionRepository.findByName(putExtensionDto.getName())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 확장자입니다.")
                );
    }
    private Extensions validPinExtensionCheck(PutExtensionDto putExtensionDto) {
        Extensions extensions = getExtensionByName(putExtensionDto);
        if (extensions.getExtensionType() != ExtensionType.PIN) {
            throw new RuntimeException("고정 확장자가 아닙니다.");
        }
        return extensions;
    }

    @Transactional
    public ExtensionView saveCustomExtension(PostExtensionDto postExtensionDto) {
        validCustomExtensionCheck(postExtensionDto);
        return ExtensionView.from(
                extensionRepository.save(postExtensionDto.toEntity())
        );
    }

    private void validCustomExtensionCheck(PostExtensionDto extensions) {
        if (extensionRepository.countByName(extensions.getName()) > 0) {
            throw new CustomException(ErrorCode.ALREADY_SAVED_EXTENSION);
        }
        long customExtensionSize = extensionRepository.countByExtensionType(ExtensionType.CUSTOM);
        if (customExtensionSize >= 200) {
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

//    public void deleteCustomExtension(Long id) {
//        extensionRepository.deleteById(id);
//    }

    public Long deleteCustomExtension(Long id) {
        extensionRepository.deleteById(id);
        return id;
    }

    private Extensions getExtensionById(Long id) {
        return extensionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 확장자입니다.")
        );
    }
}
