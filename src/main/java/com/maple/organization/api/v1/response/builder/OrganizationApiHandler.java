package com.maple.organization.api.v1.response.builder;

import com.alviss.commons.api.model.Response;
import com.alviss.commons.exception.BadRequestException;
import com.alviss.commons.validator.CustomValidator;
import com.maple.organization.api.v1.request.OrganizationRequest;
import com.maple.organization.api.v1.response.OrganizationResponse;
import com.maple.organization.service.OrganizationService;
import com.maple.organization.service.model.OrganizationEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrganizationApiHandler {

  private final OrganizationService organizationService;
  private final OrganizationEntityMapper mapper;
  private final CustomValidator validator;

  public Response<OrganizationResponse> handleGet(final String id) {
    val entity = organizationService.get(id);
    return new Response<>(mapper.entityToResponse(entity));
  }

  public Response<List<OrganizationResponse>> handleGetAll(final Pageable pageable) {
    val page = organizationService.getAll(pageable);
    return new Response<>(
        page.get().map(mapper::entityToResponse).collect(Collectors.toList()), page);
  }

  public Response<OrganizationResponse> handlePost(final OrganizationRequest request) {
    val entity = mapper.mapForPost(request);
    validate(entity);
    return new Response<>(mapper.entityToResponse(organizationService.save(entity)));
  }

  public Response<OrganizationResponse> handlePatch(
      final String id, final OrganizationRequest request) {
    val existingEntity = organizationService.get(id);
    val updatedEntity = mapper.mapForPatch(existingEntity, request);
    validate(updatedEntity);
    return new Response<>(mapper.entityToResponse(organizationService.save(updatedEntity)));
  }

  public Response<OrganizationResponse> handlePut(
      final String id, final OrganizationRequest request) {
    val existingEntity = organizationService.get(id);
    val updatedEntity = mapper.mapForPut(existingEntity, request);
    validate(updatedEntity);
    return new Response<>(mapper.entityToResponse(organizationService.save(updatedEntity)));
  }

  public void handleDelete(final String id) {
    val entity = organizationService.get(id);
    organizationService.delete(entity);
  }

  private void validate(final OrganizationEntity entity) {
    val errors = validator.validate(entity);
    if (!errors.isEmpty()) {
      throw new BadRequestException("There are some validation errors", errors);
    }
  }
}
