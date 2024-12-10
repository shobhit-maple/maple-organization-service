package com.maple.organization.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "organization_image")
public class OrganizationImageEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @NotNull
  @NotBlank
  private String organizationId;
  @Lob
  @Size(max = 1048576, message = "Image size must not exceed 1MB")
  private byte[] fullSize;
  @Lob
  @Size(max = 128000, message = "Thumbnail size must not exceed 500KB")
  private byte[] thumbnail;
}
