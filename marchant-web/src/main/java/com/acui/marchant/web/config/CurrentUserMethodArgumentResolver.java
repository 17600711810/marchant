package com.acui.marchant.web.config;

import com.acui.marchant.web.annotator.CurrentUser;
import com.acui.marchant.web.interceptor.AuthorizationInterceptor;
import com.acui.merchant.dao.entity.MerchantInfoEntity;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(MerchantInfoEntity.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        MerchantInfoEntity merchantInfoEntity = (MerchantInfoEntity) webRequest.getAttribute(AuthorizationInterceptor.REQUEST_CURRENT_KEY, RequestAttributes.SCOPE_REQUEST);
        if (merchantInfoEntity != null) {
            return merchantInfoEntity;
        }
        throw new MissingServletRequestPartException(AuthorizationInterceptor.REQUEST_CURRENT_KEY);
    }
}
