package com.maple.organization.api.v1.response.builder;

import com.maple.organization.api.v1.request.OrganizationRequest;
import com.maple.organization.api.v1.response.OrganizationResponse;
import com.maple.organization.api.v1.response.OrganizationResponse.OrganizationData;
import com.maple.organization.service.model.OrganizationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrganizationEntityMapper {

  public OrganizationEntity mapForPost(final OrganizationRequest request) {
    return OrganizationEntity.builder()
        .name(request.getName())
        .description(request.getDescription())
        .phone(request.getPhone())
        .email(request.getEmail())
        .address(request.getAddress())
        .build();
  }

  public OrganizationEntity mapForPut(
      final OrganizationEntity entity, final OrganizationRequest request) {
    entity.setName(request.getName());
    entity.setDescription(request.getDescription());
    entity.setPhone(request.getPhone());
    entity.setEmail(request.getEmail());
    entity.setAddress(request.getAddress());
    return entity;
  }

  public OrganizationEntity mapForPatch(
      final OrganizationEntity entity, final OrganizationRequest request) {
    if (request.getName() != null && !request.getName().equals(entity.getName())) {
      entity.setName(request.getName());
    }
    if (request.getDescription() != null
        && !request.getDescription().equals(entity.getDescription())) {
      entity.setDescription(request.getDescription());
    }
    if (request.getPhone() != null && !request.getPhone().equals(entity.getPhone())) {
      entity.setPhone(request.getPhone());
    }
    if (request.getEmail() != null && !request.getEmail().equals(entity.getEmail())) {
      entity.setEmail(request.getEmail());
    }
    if (request.getAddress() != null && !request.getAddress().equals(entity.getAddress())) {
      entity.setAddress(request.getAddress());
    }
    return entity;
  }

  public OrganizationResponse entityToResponse(final OrganizationEntity entity) {
    return OrganizationResponse.builder()
        .id(entity.getId())
        .data(
            OrganizationData.builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .build())
        .build();
  }
}
