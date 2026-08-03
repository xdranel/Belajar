package gendhiramona.webmvc.resolver;

import gendhiramona.webmvc.model.entity.Partner;
import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;

@Component
public class PartnerArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == Partner.class;
    }

//    @Override
//    public @Nullable Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer, NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {
//        HttpServletRequest servletRequest = (HttpServletRequest) webRequest.getNativeRequest();
//        String apiKey = servletRequest.getHeader("X-API-KEY");
//
//        if (apiKey != null){
//            // query database
//            return new Partner(apiKey, "Sample Partner");
//        } else {
//            return null;
//        }
//    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String apiKey = servletRequest != null ? servletRequest.getHeader("X-API-KEY") : null;

        if (apiKey == null || apiKey.isBlank()) {
            // Throw an explicit HTTP exception instead of returning null
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing X-API-KEY header");
        }

        // Query database...
        return new Partner(apiKey, "Sample Partner");
    }
}
