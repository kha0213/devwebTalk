package com.example.devwebtalk.setting.config;

import com.example.devwebtalk.setting.constant.Cons;
import com.example.devwebtalk.vo.LoginVO;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.example.devwebtalk.setting.constant.Cons.*;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-28
 * Time: 오전 12:10
 */
@Configuration
public class JpaAuditorAwareConfig implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .map(requsetAttribute -> requsetAttribute.getRequest())
                .map(request -> request.getSession(false))
                .map(session -> (LoginVO) session.getAttribute(LOGIN_VO))
                .map(loginVO -> loginVO.getEmail());
    }
}
