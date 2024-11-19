package com.maple.organization.service;

import com.maple.organization.service.model.OrganizationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrganizationService {

  OrganizationEntity get(String id);

  Page<OrganizationEntity> getAll(Pageable pageable);

  OrganizationEntity save(OrganizationEntity entity);

  void delete(OrganizationEntity entity);
}
