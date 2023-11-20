package com.faxd.effirecord.auth;

import com.faxd.effirecord.model.Employee;
import com.faxd.effirecord.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final String PATTERN_MATCH_PHONE = "^\\d+$";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employeeOptional;
        if (username.matches(PATTERN_MATCH_PHONE)) {
            employeeOptional = employeeRepository.findByPhoneAndIsDeletedFalseAndIsVerifiedTrue(username);
        } else {
            employeeOptional = employeeRepository.findByNameAndIsDeletedFalseAndIsVerifiedTrue(username);
        }
        if (employeeOptional.isEmpty()){
            logger.info("Can not found the employee information with name or phone: {}", username);
            throw  new UsernameNotFoundException("Can not found the employee information with name: " + username);
        }
        Employee employee = employeeOptional.orElseThrow(() -> {
            logger.info("Can not found the employee information with name or phone: {}", username);
            return new UsernameNotFoundException("Can not found the employee information with name: " + username);
        });

        List<Permission> permissionList = employee.getRoles().stream()
                .flatMap(role -> role.getAuthorities().stream()
                        .map(authority ->
                                Permission.builder()
                                        .entityId(role.getEntityId())
                                        .entityType(role.getEntityType())
                                        .permission(authority.getName())
                                        .build()))
                .toList();

        return EffiRecordUserDetails.builder()
                .id(employee.getId())
                .username(employee.getName())
                .password(employee.getPassword())
                .authorities(permissionList)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(employee.isVerified())
                .build();
    }

}
