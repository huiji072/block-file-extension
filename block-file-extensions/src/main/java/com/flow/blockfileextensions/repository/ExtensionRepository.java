package com.flow.blockfileextensions.repository;

import com.flow.blockfileextensions.constant.ExtensionType;
import com.flow.blockfileextensions.entity.Extensions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExtensionRepository extends JpaRepository<Extensions, Long> {
    Optional<Extensions> findById(Long id);
    Optional<Extensions> findByName(String name);
    List<Extensions> findByExtensionType(ExtensionType extensionType);
    long countByExtensionType(ExtensionType extensionType);
    List<Extensions> findAll();
}
