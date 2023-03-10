package com.flow.blockfileextensions.entity;

import com.flow.blockfileextensions.constant.ExtensionType;
import com.flow.blockfileextensions.dto.PostExtensionDto;
import com.flow.blockfileextensions.dto.PutExtensionDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "extensions")
public class Extensions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean blocked;
    @Enumerated(EnumType.STRING)
    private ExtensionType extensionType;

}
