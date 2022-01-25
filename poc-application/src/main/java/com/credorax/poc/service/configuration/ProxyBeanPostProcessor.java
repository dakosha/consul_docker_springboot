package com.credorax.poc.service.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public abstract class ProxyBeanPostProcessor implements BeanPostProcessor {

    private static final Set<Class> proxyAnnotations = new HashSet<>();
    private static final Set<Class> proxyMainAnnotation = new HashSet<>();

    static {
        proxyMainAnnotation.add(Proxied.class);

        proxyAnnotations.add(RequestMapping.class);
        proxyAnnotations.add(GetMapping.class);
        proxyAnnotations.add(PostMapping.class);
    }

    @Autowired
    private ProxyConfigurationService proxyConfigurationService;
    @Autowired
    private RoutingProxyService routingProxyService;

    private static boolean isAnnotated(Annotation[] annotations, Set<Class> annotationClass) {
        if (annotations == null || annotations.length == 0) {
            return false;
        }
        for (Annotation annotation : annotations) {
            if (annotationClass.contains(annotation.annotationType())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class originalClass = bean.getClass();
        if (isAnnotated(originalClass.getAnnotations(), proxyMainAnnotation) && proxyConfigurationService.getShouldWeProxy()) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(originalClass);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
                if (isAnnotated(method.getAnnotations(), proxyAnnotations)) {
                    HttpServletRequest request = findHttpRequest(objects);
                    if (request != null && shouldBeProxied(request)) {
                        return routingProxyService.proxyMethod(request, method.getReturnType());
                    } else {
                        return method.invoke(bean, objects);
                    }
                } else {
                    return method.invoke(bean, objects);
                }
            });
            return enhancer.create();
        } else {
            return bean;
        }
    }

    /**
     * Here should be an implementation that decides, should we proxy OR not.
     * It is left for Orly
     * <p>
     * Right now it is a temp implementation. Should be rewritten
     *
     * @param request
     * @return
     */
    abstract boolean shouldBeProxied(HttpServletRequest request);

    private HttpServletRequest findHttpRequest(Object[] objects) {
        if (objects == null || objects.length == 0) {
            return null;
        }
        HttpServletRequest request = null;
        for (Object object : objects) {
            if (object instanceof HttpServletRequest) {
                return (HttpServletRequest) object;
            }
        }
        return null;
    }

}
