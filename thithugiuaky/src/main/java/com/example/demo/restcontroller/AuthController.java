package com.example.demo.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import com.example.demo.authen.UserPrincipal;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleToUserFrom;
import com.example.demo.entity.Token;
import com.example.demo.entity.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RoleService roleService;
    
    @PostMapping("/register")
    public User register(@RequestBody User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return userService.createUser(user);
    }
    
    @PostMapping("/addRole")
    public ResponseEntity<?> addRole(@RequestBody List<RoleToUserFrom> roleForms){
    	List<User> users = new ArrayList<>();
    	for (RoleToUserFrom roleForm : roleForms) {
    		User user = userService.getUserByUsername(roleForm.getUsername());
        	if (user != null) {
        		Set<Role> roles = user.getRoles();
        		roleService.addRole(new Role(0l, "", null, null, 0l, 0l, roleForm.getRoleName(), roleForm.getRoleKey(), null));
        		roles.add(roleService.findRoleByRoleName(roleForm.getRoleName()));
        		user.setRoles(roles);
        		users.add(userService.saveUser(user));
        	}
		}
    	return ResponseEntity.ok(users);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){

        UserPrincipal userPrincipal =
                userService.findByUsername(user.getUsername());

        if (null == user || !new BCryptPasswordEncoder()
                .matches(user.getPassword(), userPrincipal.getPassword())) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Account or password is not valid!");
        }

        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));

        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setCreatedBy(userPrincipal.getUserId());
        tokenService.createToken(token);

        return ResponseEntity.ok(token.getToken());
    }

    
    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('READ')")
    public ResponseEntity hello(){
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/onlyReadPage")
    @PreAuthorize("hasAnyAuthority('USER_READ')")
    public ResponseEntity onlyRead(){
        return ResponseEntity.ok("only USER_READ");
    }

    @GetMapping("/crudPage")
    @PreAuthorize("hasAnyAuthority('USER_CRUD')")
    public ResponseEntity onlyCRUD(){
        return ResponseEntity.ok("only USER_CRUD");
    }


//    Object principal = SecurityContextHolder
//            .getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//        UserPrincipal userPrincipal = (UserPrincipal) principal;
//    }

}
