package life.bienao.springbootinit.constant;

/**
 * 描述：白名单
 */
public class AuthWhiteList {

    /**
     * 需要放行的URL
     */
    public static final String[] AUTH_WHITELIST = {
            // -- register url
            "/auth/login",
            "/auth/emailCode",
            "/auth/register",
            "/auth/resetPassword",
            "/view/**",
            "/assets/**",
            "/img/**",
            "/tinymce/**",
            "/",
            // other public endpoints of your API may be appended to this array
    };
}
