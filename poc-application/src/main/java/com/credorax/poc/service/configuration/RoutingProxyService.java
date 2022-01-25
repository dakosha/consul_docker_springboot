package com.credorax.poc.service.configuration;

import com.credorax.poc.service.tools.TypeResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class RoutingProxyService {

    private static Logger LOGGER = LoggerFactory.getLogger(RoutingProxyService.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProxyConfigurationService proxyConfigurationService;

    public Object proxyMethod(HttpServletRequest request, Class responseClass) {
        HttpMethod httpMethod = HttpMethod.resolve(request.getMethod());
        String scheme = request.getScheme();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        String queryString = request.getQueryString();

        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://");

        url.append(proxyConfigurationService.getNextHost());

        url.append(contextPath).append(servletPath);

        if (pathInfo != null) {
            url.append(pathInfo);
        }
        if (queryString != null) {
            url.append("?").append(queryString);
        }

        AtomicBoolean flag = new AtomicBoolean(false);
        Map<String, List<String>> headers = new LinkedHashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        headerNames.asIterator().forEachRemaining(headerName -> {
            Enumeration<String> headerValues = request.getHeaders(headerName);
            headerValues.asIterator().forEachRemaining(value -> {
                List<String> values = headers.computeIfAbsent(headerName, k -> new ArrayList<>());
                values.add(value);
                if (headerName.equals("source") && value.equals("amazing-hpp")) {
                    flag.set(true);
                }
            });
        });
        if (flag.get()) {
            return "Cyclic invocation";
        }

        LOGGER.info(headers.toString());

        LOGGER.info(url.toString());
        URI uri = URI.create(url.toString());

        RequestEntity.HeadersBuilder<?> headersBuilder = RequestEntity.method(httpMethod, uri);
        headers.forEach((name, values) -> {
            values.forEach(value -> {
                headersBuilder.header(name, value);
            });
        });
        headersBuilder.header("source", "amazing-hpp");
        RequestEntity<Void> requestEntity = headersBuilder.build();

        if (ResponseEntity.class.equals(responseClass)) {
            ResponseEntity<Object> result = restTemplate.exchange(requestEntity, Object.class);
            return result;
        } else {
            ResponseEntity result = restTemplate.exchange(requestEntity, responseClass);
            return result.getBody();
        }
    }

}
