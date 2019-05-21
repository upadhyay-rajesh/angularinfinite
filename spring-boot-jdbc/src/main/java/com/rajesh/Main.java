package com.rajesh;
import com.rajesh.dao.CustomerRepository;
import com.rajesh.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.sql.DataSource;
import java.util.List;
import static java.lang.System.exit;
@SpringBootApplication
public class Main implements CommandLineRunner {
        
    @Autowired
    CustomerRepository customerRepository;
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
       
        System.out.println("Display all customers...");
        List<Customer> list = customerRepository.findAll();
        
        for(Customer c:list){
        	System.out.println(c);
        }
        
        list.forEach(x -> System.out.println(x));
        System.out.println("Done!");
        exit(0);
    }
}