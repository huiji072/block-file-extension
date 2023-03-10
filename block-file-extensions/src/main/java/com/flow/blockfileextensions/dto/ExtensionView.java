package com.flow.blockfileextensions.dto;

import com.flow.blockfileextensions.constant.ExtensionType;
import com.flow.blockfileextensions.entity.Extensions;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
public class ExtensionView {
    private Long id;
    private String name;
    private Boolean blocked;
    private ExtensionType extensionType;

    public static ExtensionView from(Extensions extensions) {
        return ExtensionView.builder()
                .id(extensions.getId())
                .name(extensions.getName())
                .extensionType(extensions.getExtensionType())
                .build();
    }
}
