package com.LMS.userManagement.tenantConfigBySpringIO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class HttpHeaderTenantResolver implements TenantResolver<HttpServletRequest> {

    //  private  final TenantHttpProperties tenantHttpProperties;
    @Override
    public String resolveTenantId(HttpServletRequest request) {
        String tenantHeader = request.getHeader("tenantId");
        if (tenantHeader.isEmpty()) {
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String tenantId;
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    return null;
                }

                jwt = authHeader.substring(7);

            return null;
        }
        return tenantHeader;
    }
}
