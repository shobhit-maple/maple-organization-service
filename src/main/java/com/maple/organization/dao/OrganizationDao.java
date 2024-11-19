package com.maple.organization.dao;

import com.maple.organization.service.model.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationDao extends JpaRepository<OrganizationEntity, String> {
}
