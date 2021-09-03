package com.example.devwebtalk.setting.util;

import com.example.devwebtalk.setting.constant.Cons;
import com.example.devwebtalk.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.example.devwebtalk.setting.constant.Cons.LOGIN_VO;

/**
 * 세션과 관련된 유틸 클래스
 * 2021-07-26
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@Slf4j
public class SessionUtil {
    /**
     * 로그인 여부 확인하기 (true면 guest)
     * @author kyl
     * @param req
     * @return
     */
    public static boolean isGuestUser(HttpServletRequest req) {
        return getLoginVO(req) == null;
    }

    /**
     * 로그인 여부 확인하기 (true면 login)
     * @author kyl
     * @param req
     * @return
     */
    public static boolean isLoginUser(HttpServletRequest req) {
        return getLoginVO(req) != null;
    }


    /**
     * loginVO 가져오기
     * @author kyl
     * @param req
     * @return LoginVO
     */
    public static LoginVO getLoginVO(HttpServletRequest req) {
        Object result = getSessionValue(req, LOGIN_VO);
        if(result instanceof LoginVO) return (LoginVO) result;
        else return null;
    }

    /**
     * 세션 값 가져오기 (Object)
     * @author kyl
     * @param req Request객체
     * @param value 세션에서 검색할 값
     * @return Object
     */
    public static Object getSessionValue(HttpServletRequest req, String value) {
        HttpSession session = req.getSession(false);
        if(session == null) return null;
        return session.getAttribute(value);
    }

    /**
     * 세션에서 String값 자겨오기
     * @author kyl
     * @param req
     * @param value
     * @param defaultValue
     * @return String
     */
    public static String getSessionStringValue(HttpServletRequest req, String value, String defaultValue) {
        HttpSession session = req.getSession(false);
        Object result = getSessionValue(req, value);
        if( result == null ) {
            return defaultValue;
        } else {
            return (String) result;
        }
    }

    public static String getSessionStringValue(HttpServletRequest req, String value) {
        return getSessionStringValue(req, value, null);
    }

    public static void setSessionValue(HttpServletRequest req, String key, Object value) {
        final HttpSession session = req.getSession(false);
        if( session == null ) return;
        session.setAttribute(key, value);
    }

}
