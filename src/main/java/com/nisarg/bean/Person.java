package com.nisarg.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "people")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Schema(name = "ID of a person", description = "System generated and unknown to person", requiredMode = RequiredMode.REQUIRED)
  long id;
  @NotBlank
  @Schema(name = "Full Name of a person", description = "Full Name", requiredMode = RequiredMode.REQUIRED)
  String name;
  @Email
  @Schema(name = "Email of a person", description = "Must be a valid email address", requiredMode = RequiredMode.REQUIRED)
  String email;
  @Min(1)
  @Schema(name = "Person's age", description = "Age must be 18 or above, no minors", requiredMode = RequiredMode.REQUIRED)
  int age;
  @Schema(name = "Person's gender", description = "Refers to birth-time biological gender (Male/Female)", requiredMode = RequiredMode.REQUIRED)
  String gender;
}
