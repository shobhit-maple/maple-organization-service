package com.maple.organization.api.v1.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationResponse {

  private String id;
  private OrganizationData data;

  @Data
  @Builder
  public static class OrganizationData {
    private String name;
    private String description;
    private String phone;
    private String email;
    private String address;
  }
}
