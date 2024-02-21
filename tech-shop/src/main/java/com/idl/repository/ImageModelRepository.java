package com.idl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idl.models.ImageModel;

@Repository
public interface ImageModelRepository extends JpaRepository<ImageModel, Long> {

}
