package com.pethome.jwt;

public enum JwtAuthorityStrategy {
    /**
     * 默认所有的请求都需要验证jwt，使用@JwtAuthority(enabled = false)注解的url，则不验证jwt
     */
    ALL_AUTHORITY,
    /**
     * 默认所有请求都不验证jwt，使用@JwtAuthority(enabled = true)注解的url，则验证jwt
     */
    ALL_IGNORE,
    /**
     * 关闭所有请求的jwt验证，忽略所有注解，即使标注了@JwtAuthority注解也不验证jwt，仅供测试使用，不建议在生产环境使用
     */
    NOT_USED
}