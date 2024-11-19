package com.maple.organization.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "organization")
public class OrganizationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @NotNull
  @NotBlank
  private String name;
  private String description;
  @NotNull
  @NotBlank
  private String phone;
  @NotNull
  @NotBlank
  private String email;
  @NotNull
  @NotBlank
  private String address;
}
