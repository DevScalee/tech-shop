package com.idl.models;

	
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="Image")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(name = "picbyte",length=100000)
    private byte[] picByte;

    @ManyToOne
    private  Product product;
    
    public ImageModel(String name, String type, byte[] picByte) {
    	this.name = name;
		this.type = type;
		this.picByte = picByte;
	}
    
    public ImageModel(Long id,String name, String type, byte[] picByte) {
		this.id=id;
		this.name = name;
		this.type = type;
		this.picByte = picByte;
	}
    public ImageModel() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
	
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
    
    
}