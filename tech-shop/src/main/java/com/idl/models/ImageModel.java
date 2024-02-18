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
}