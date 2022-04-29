package dev.sunriseydy.blog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import dev.sunriseydy.blog.db.typecho.entity.TypechoContents;
import dev.sunriseydy.blog.db.typecho.mapper.TypechoContentsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-29 13:03
 */
@SpringBootTest
public class SyBlogPostTests {
    @Resource
    private TypechoContentsMapper contentsMapper;

    @Test
    public void getPostHtml() throws Exception {
        List<TypechoContents> typechoContents = contentsMapper.selectList(new QueryWrapper<TypechoContents>().lambda()
                .select(TypechoContents::getSlug, TypechoContents::getText, TypechoContents::getCid)
                .eq(TypechoContents::getStatus, "publish"));
        for (TypechoContents object : typechoContents) {
            String slug = object.getSlug();
            Integer cid = object.getCid();
            System.out.println(slug);
            OutputStreamWriter writer = new FileWriter("/tmp/blog/html/" + cid + "#" + slug + ".html");
            String text = object.getText();
            text = text.replaceAll("\\[get_video_link reply=\"(.*)\"\\]", "<p>针对本次教程我也录制了视频教程，如有需要可以使用微信扫一扫文章下方左边的二维码或者搜索\"sunriseydy\"关注本站的微信公众号然后回复<code>\"$1\"</code>即可获取视频观看链接。</p>\n")
                    .replace("https://cdn.sunriseydy.top/wp-content/uploads", "https://blog-cdn.sunriseydy.dev/wp-content/uploads")
                    .replace("https://blog.sunriseydy.top/wp-content/uploads", "https://blog-cdn.sunriseydy.dev/wp-content/uploads");
            object.setText(text);
            writer.write(text);
            writer.flush();
            writer.close();
            contentsMapper.update(null, new UpdateWrapper<TypechoContents>().lambda().eq(TypechoContents::getCid, object.getCid())
                    .set(TypechoContents::getText, object.getText()));
        }
    }
}
