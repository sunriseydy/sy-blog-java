package dev.sunriseydy.blog.user.domain.vo;

import dev.sunriseydy.blog.db.typecho.entity.TypechoUsers;
import dev.sunriseydy.blog.user.api.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author SunriseYDY
 * @date 2022-04-30 14:52
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TpDbUserVO {

    private Long id;

    private String name;

    private String mail;

    private String url;

    private String slug;

    public static TpDbUserVO fromTypechoUser(TypechoUsers user) {
        return TpDbUserVO.builder()
                .id(Long.valueOf(user.getUid()))
                .name(user.getScreenName())
                .mail(user.getMail())
                .url(user.getUrl())
                .slug(user.getName())
                .build();
    }

    public UserDTO toUserDTO() {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
