package com.maple.organization.service;

import com.maple.organization.service.model.OrganizationImageEntity;

public interface OrganizationImageService {

  OrganizationImageEntity get(String organizationId);

  OrganizationImageEntity save(OrganizationImageEntity entity);

  void delete(OrganizationImageEntity entity);
}
