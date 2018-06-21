package ragna.argumentresolver;

import javax.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class InsightHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Insight.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {


        String[] deepObjects = webRequest.getParameterValues("insights");

        HttpServletRequest nativeRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        String queryString = nativeRequest.getQueryString();

        // parse this one to type parameter
        return defaultInsight();
    }

    private Insight defaultInsight() {
        return new Insight("defaultBar", "defaultFoo");
    }
}
