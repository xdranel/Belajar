package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CreateOrUpdateTodolist
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-08-31T09:32:51.718492283+07:00[Asia/Jakarta]", comments = "Generator version: 7.24.0")
public class CreateOrUpdateTodolist {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private @Nullable String name;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private BigDecimal priority = new BigDecimal("1");

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<String> tags = new ArrayList<>();

  public CreateOrUpdateTodolist name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  
  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(@Nullable String name) {
    this.name = name;
  }

  public CreateOrUpdateTodolist priority(BigDecimal priority) {
    this.priority = priority;
    return this;
  }

  /**
   * Get priority
   * @return priority
   */
  @Valid 
  @Schema(name = "priority", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("priority")
  public BigDecimal getPriority() {
    return priority;
  }

  @JsonProperty("priority")
  public void setPriority(BigDecimal priority) {
    this.priority = priority;
  }

  public CreateOrUpdateTodolist tags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  public CreateOrUpdateTodolist addTagsItem(String tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<>();
    }
    this.tags.add(tagsItem);
    return this;
  }

  /**
   * Get tags
   * @return tags
   */
  
  @Schema(name = "tags", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tags")
  public List<String> getTags() {
    return tags;
  }

  @JsonProperty("tags")
  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateOrUpdateTodolist createOrUpdateTodolist = (CreateOrUpdateTodolist) o;
    return Objects.equals(this.name, createOrUpdateTodolist.name) &&
        Objects.equals(this.priority, createOrUpdateTodolist.priority) &&
        Objects.equals(this.tags, createOrUpdateTodolist.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, priority, tags);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateOrUpdateTodolist {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(@Nullable Object o) {
    return o == null ? "null" : o.toString().replace("\n", "\n    ");
  }
}

