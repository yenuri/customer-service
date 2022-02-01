package com.bootcamp.customer.model;

import com.bootcamp.customer.utils.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customer")
public class Customer {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String dni;
    private String customerType;
    private String address;
    private String phone;
    private String status;
    private Date CreationDateAt;
}
