package sunnie.jussback;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class HelloController {
    // @RequestParam String param
    @GetMapping("/")
    public String home() {
        return "{\"hello\" : \"spring\"}";
    }
    // @PostMapping("path")
    // public String postMethodName(@RequestBody String entity) {
    //     //TODO: process POST request
        
    //     return entity;
    // }
    
}
