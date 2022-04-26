package dev.sunriseydy.blog.user.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author SunriseYDY
 * @date 2022-04-26 16:49
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -5091784975660614769L;

    private Long id;

    private String name;

    private String url;

    private String description;

    private String link;

    private String slug;

    private String avatarUrl;
}
