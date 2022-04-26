package dev.sunriseydy.blog.user.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.sunriseydy.blog.user.api.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author SunriseYDY
 * @date 2022-04-26 16:45
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WpApiUserVO implements Serializable {
    private static final long serialVersionUID = -9022389995714747638L;

    private Long id;

    private String name;

    private String url;

    private String description;

    private String link;

    private String slug;

    private ObjectNode avatarUrls;

    public UserDTO toUserDTO() {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(this, dto);
        // 设置 avatarUrl
        if (avatarUrls != null
                && avatarUrls.isObject()
                && avatarUrls.hasNonNull("96")) {
            String avatarUrl = avatarUrls.get("96").asText();
            dto.setAvatarUrl(avatarUrl);
        }
            return dto;
    }
}
