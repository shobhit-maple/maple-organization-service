package com.maple.organization.api.v1.controller;

import com.maple.organization.api.v1.request.OrganizationImageRequest;
import com.maple.organization.api.v1.response.builder.OrganizationImageApiHandler;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("OrganizationImageControllerV1")
@AllArgsConstructor
@RequestMapping("/api/v1/organizations/{organizationId}/images")
public class OrganizationImageController {

  private final OrganizationImageApiHandler apiHandler;

  @GetMapping("/full-size")
  public ResponseEntity<byte[]> getFullImage(@PathVariable final String organizationId) {
    return apiHandler.handleGetFullSize(organizationId);
  }

  @GetMapping("/thumbnail")
  public ResponseEntity<byte[]> getThumbnail(@PathVariable final String organizationId) {
    return apiHandler.handleGetThumbnail(organizationId);
  }

  @PostMapping()
  public void post(
      @PathVariable final String organizationId,
      @RequestBody final OrganizationImageRequest request)
      throws IOException {
    apiHandler.handlePost(organizationId, request);
  }

  @PatchMapping()
  public void patch(
      @PathVariable final String organizationId,
      @RequestBody final OrganizationImageRequest request)
      throws IOException {
    apiHandler.handlePatch(organizationId, request);
  }

  @DeleteMapping()
  public void delete(@PathVariable final String organizationId) {
    apiHandler.handleDelete(organizationId);
  }
}
