package com.rajesh.dao;
import com.rajesh.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import java.sql.*;

@Repository
public class CustomerRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    DataSource dataSource;

    public List<Customer> findAll() {
       List<Customer> result = jdbcTemplate.query(
                "SELECT id, name, email, created_date FROM customer1",
                (rs, rowNum) -> new Customer(rs.getInt("id"),
                        rs.getString("name"), rs.getString("email"), 
                        rs.getDate("created_date"))
        );
        
        List<Customer> ll=new ArrayList<Customer>();
        try{
        Connection con=dataSource.getConnection();
        PreparedStatement ps=con.prepareStatement(
        		"SELECT id, name, email, created_date FROM customer1");
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
        	Customer c=new Customer(rs.getInt("id"),rs.getString("name"), 
        			rs.getString("email"),rs.getDate("created_date"));
        	ll.add(c);
        }
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        return ll;
    }
}
