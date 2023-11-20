//package com.faxd.effirecord.auth;
//
//
//import com.faxd.effirecord.exception.MyAccessDeniedException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authorization.AuthorizationDecision;
//import org.springframework.security.authorization.AuthorizationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Collection;
//import java.util.List;
//import java.util.function.Supplier;
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//public class MyAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
//
//
//    @Override
//    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
//        AuthorizationManager.super.verify(authentication, requestAuthorizationContext);
//    }
//
//    @Override
//    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
//        // 我们可以获取原始request对象
//        HttpServletRequest request = requestAuthorizationContext.getRequest();
//
//        String requestURI = request.getRequestURI();
//
//
//        // 根据这些信息 和业务写逻辑即可 最终决定是否授权 isGranted
//        boolean isGranted = true;
//
//        if(requestURI.equals("/login") || requestURI.equals("/logout")){
//            return new AuthorizationDecision(isGranted);
//        }
//
//        // 当前用户的权限信息 比如角色
//        Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();
//        List<String> userCodes = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
//        //当前被访问的URL:需要哪些权限才能访问
////        List<String> codes = roleMapper.findCodesByUrl(requestURI);
////
////        if(codes == null || codes.size()==0 || !contains(codes,userCodes)){
////            throw new MyAccessDeniedException("没有权限");
////        }
//
//        return new AuthorizationDecision(isGranted);
//    }
//
//    public boolean contains(List<String> srcs,List<String> dests){
//        for (String src : srcs) {
//            for (String dest : dests) {
//                if(src.equals(dest)){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//}
