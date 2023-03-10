package com.flow.blockfileextensions.dto;

import com.flow.blockfileextensions.constant.ExtensionType;
import com.flow.blockfileextensions.entity.Extensions;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateExtensionDto {
    @Column(unique = true)
    @Size(min = 1, max = 20, message = "1자 이상 20자 이하로 입력해주세요.")
    private String name;

    public Extensions toEntity() {
        return Extensions.builder()
                .extensionType(ExtensionType.CUSTOM)
                .blocked(false)
                .name(name)
                .build();
    }
}
