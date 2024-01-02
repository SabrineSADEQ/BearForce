package fr.isika.cda.javaee.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="payment_receipt")

public class PaymentReceipt {
	
@Id
@GeneratedValue (strategy=GenerationType.AUTO)
private long id;

}
