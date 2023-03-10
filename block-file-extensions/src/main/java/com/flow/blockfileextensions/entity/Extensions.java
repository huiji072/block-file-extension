package com.flow.blockfileextensions.entity;

import com.flow.blockfileextensions.constant.ExtensionType;
import com.flow.blockfileextensions.dto.PostExtensionDto;
import com.flow.blockfileextensions.dto.PutExtensionDto;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "extensions")
public class Extensions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean blocked;
    @Enumerated(EnumType.STRING)
    private ExtensionType extensionType;

    public static Extensions createCustomExtensions(PostExtensionDto extensionsDto) {
        Extensions extensions = new Extensions();
        extensions.setName(extensionsDto.getName());
        extensions.setBlocked(false);
        return extensions;
    }
}
