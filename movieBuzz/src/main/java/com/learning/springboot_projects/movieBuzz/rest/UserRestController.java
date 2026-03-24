package com.learning.springboot_projects.movieBuzz.rest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.learning.springboot_projects.movieBuzz.entity.Role;
import com.learning.springboot_projects.movieBuzz.entity.User;
import com.learning.springboot_projects.movieBuzz.service.RoleService;
import com.learning.springboot_projects.movieBuzz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")  // Base path for this controller
public class UserRestController {

    private UserService theUserService;

    private ObjectMapper theObjectMapper;

    private RoleService theRoleService;

    @Autowired
    public UserRestController(UserService userService,ObjectMapper objectMapper,RoleService roleService){
        theUserService=userService;
        theObjectMapper=objectMapper;
        theRoleService=roleService;
    }

    //add mapping for GET/user/{userId}

    @GetMapping("/user/{userId}")
    public User findById(@PathVariable int userId){
        User theUser = theUserService.findById(userId);

        if(theUser==null)
        {
            throw new RuntimeException("User id not found - "+ userId);
        }

        return theUser;
    }

    //add mapping for POST /user - add new employee
    @PostMapping("/register")
    public User addCustomerUser(@RequestBody User theUser){

        //also in case they pass some non zero id in json .... then set id explicitly to 0
        //this is to force a save of new item instead of update

        theUser.setId(0);

        User dbUser=theUserService.save(theUser,"ROLE_CUSTOMER");
        return dbUser;
    }

    @PostMapping("/createAdminUser")
    public User addAdminUser(@RequestBody User theUser){

        //also in case they pass some non zero id in json .... then set id explicitly to 0
        //this is to force a save of new item instead of update

        theUser.setId(0);

        User dbUser=theUserService.save(theUser,"ROLE_ADMIN");
        return dbUser;
    }



    //add mapping for PATCH /user/{userId} - patch user ......patch update
    @PatchMapping("/user/{userId}")
    public User patchUser(@PathVariable int userId,
                          @RequestBody Map<String,Object> patchPayload){
        User tempUser = theUserService.findById(userId);

        //throw exception if null
        if(tempUser==null){
            throw new RuntimeException("User id not found - "+userId);
        }

        //throw exception if request body contains "id" key
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("User id not allowed in patch payload - "+userId);
        }

        Role tempRole = theRoleService.getRoleByUserName(tempUser.getEmail());

        User patchedUser = apply(patchPayload,tempUser);

        User dbUser = theUserService.save(patchedUser,tempRole.getRole());



        return dbUser;
    }

    private User apply(Map<String, Object> patchPayload, User tempUser) {
        //convert user object to a json object node
        ObjectNode userNode = theObjectMapper.convertValue(tempUser, ObjectNode.class);

        //convert patchpayload object to a json object node
        ObjectNode patchNode = theObjectMapper.convertValue(patchPayload,ObjectNode.class);

        //Merge the patch updates into the user node
        userNode.setAll(patchNode);

        return theObjectMapper.convertValue(userNode,User.class);

    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return theUserService.verify(user);
    }


}
