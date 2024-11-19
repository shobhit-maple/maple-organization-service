package com.maple.organization.api.v1.controller;

import com.alviss.commons.api.model.Response;
import com.alviss.commons.security.SecurityService;
import com.maple.organization.api.v1.request.OrganizationRequest;
import com.maple.organization.api.v1.response.OrganizationResponse;

import com.maple.organization.api.v1.response.builder.OrganizationApiHandler;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("OrganizationControllerV1")
@AllArgsConstructor
@RequestMapping("/api/v1/organizations")
public class OrganizationController {

  private final OrganizationApiHandler apiHandler;
  private final SecurityService securityService;

  @GetMapping("/{id}")
  //  @PreAuthorize("@securityService.hasPermission('ROLE_ORGANIZATION_READ')")
  public Response<OrganizationResponse> get(@PathVariable final String id) {
    return apiHandler.handleGet(id);
  }

  @GetMapping
  //  @PreAuthorize("@securityService.hasPermission('ROLE_ORGANIZATION_READ')")
  public Response<List<OrganizationResponse>> getAll(final Pageable pageable) {
    return apiHandler.handleGetAll(pageable);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  //  @PreAuthorize("@securityService.hasPermission('ROLE_ORGANIZATION_CREATE')")
  public Response<OrganizationResponse> post(@RequestBody final OrganizationRequest request) {
    return apiHandler.handlePost(request);
  }

  @PutMapping("/{id}")
  //  @PreAuthorize("@securityService.hasPermission('ROLE_ORGANIZATION_UPDATE')")
  public Response<OrganizationResponse> put(
      @PathVariable final String id, @RequestBody final OrganizationRequest request) {
    return apiHandler.handlePut(id, request);
  }

  @PatchMapping("/{id}")
  //  @PreAuthorize("@securityService.hasPermission('ROLE_ORGANIZATION_UPDATE')")
  public Response<OrganizationResponse> patch(
      @PathVariable final String id, @RequestBody final OrganizationRequest request) {
    return apiHandler.handlePatch(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  //  @PreAuthorize("@securityService.hasPermission('ROLE_ORGANIZATION_DELETE')")
  public void delete(@PathVariable final String id) {
    apiHandler.handleDelete(id);
  }
}
