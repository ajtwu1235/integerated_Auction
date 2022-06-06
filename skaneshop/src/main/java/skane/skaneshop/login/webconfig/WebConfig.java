package skane.skaneshop.login.webconfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import skane.skaneshop.login.application.ArgumentResolver.LoginMemberArgumentResolver;
import skane.skaneshop.login.application.interceptor.LogInterceptor;
import skane.skaneshop.login.application.interceptor.LoginCheckInterceptor;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","/img/**","/*.ico","/error");

        // 비로그인 사용자 제한 목록
        // 220605 - 이미지파일도 접근가능하게 해둿음
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/members/add","/login","/logout",
                        "/css/**","/img/**","/*.ico","/error");
    }

}
