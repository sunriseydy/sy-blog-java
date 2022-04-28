package dev.sunriseydy.blog.common.controller;

import dev.sunriseydy.blog.common.Result;
import dev.sunriseydy.blog.common.annotion.BasicAuth;
import dev.sunriseydy.blog.common.convert.WordPressToTypechoConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SunriseYDY
 * @date 2022-04-28 15:37
 */
@RestController
@RequestMapping("/convert/wp2tp")
public class WordPressToTypechoController {
    @Autowired
    private WordPressToTypechoConvert wordPressToTypechoConvert;

    @GetMapping
    @BasicAuth
    public Result<String> convert() {
        String message = wordPressToTypechoConvert.convert();
        return Result.ok(message);
    }
}
