package com.maple.organization.dao;

import com.maple.organization.service.model.OrganizationImageEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationImageDao extends JpaRepository<OrganizationImageEntity, String> {

  Optional<OrganizationImageEntity> findByOrganizationId(String organizationId);
}
