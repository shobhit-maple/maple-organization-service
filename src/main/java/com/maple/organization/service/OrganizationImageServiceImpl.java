package com.maple.organization.service;

import com.alviss.commons.exception.NotFoundException;
import com.maple.organization.dao.OrganizationImageDao;
import com.maple.organization.service.model.OrganizationImageEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationImageServiceImpl implements OrganizationImageService {

  private final OrganizationImageDao dao;

  @Override
  public OrganizationImageEntity get(final String organizationId) {
    return dao.findByOrganizationId(organizationId)
        .orElseThrow(() -> new NotFoundException("Requested organization image is not found"));
  }

  @Override
  public OrganizationImageEntity save(final OrganizationImageEntity entity) {
    return dao.save(entity);
  }

  @Override
  public void delete(final OrganizationImageEntity entity) {
    dao.delete(entity);
  }
}
