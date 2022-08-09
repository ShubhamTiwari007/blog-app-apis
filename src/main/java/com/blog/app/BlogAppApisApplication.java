package com.blog.app;

import com.blog.app.config.AppConstant;
import com.blog.app.entities.Role;
import com.blog.app.repositries.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

    @Autowired
    private RoleRepo roleRepo;
    public static void main(String[] args) {
        SpringApplication.run(BlogAppApisApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Override
    public void run(String... args) throws Exception {
        // creating ADMIN and NORMAL role
        try{

            Role role1 = new Role();
            role1.setId(AppConstant.ADMIN_USER);
            role1.setName("ADMIN_USER");

            Role role2 = new Role();
            role2.setId(AppConstant.NORMAL_USER);
            role2.setName("NORMAL_USER");

            List<Role> roles = new ArrayList<>();
            roles.add(role1);
            roles.add(role2);

            this.roleRepo.saveAll(roles);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
