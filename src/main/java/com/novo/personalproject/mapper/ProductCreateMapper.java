package com.novo.personalproject.mapper;

import com.novo.personalproject.dto.ProductCreateDto;
import com.novo.personalproject.model.entity.Product;
import com.novo.personalproject.service.GoogleCloudService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductCreateMapper implements Mapper<ProductCreateDto, Product> {
    @Autowired
    private final GoogleCloudService googleCloudService;

    @Override
    public Product map(ProductCreateDto object) {
        Product product = new Product();
        copyAndSave(object, product);
        return product;
    }

    private void copyAndSave(ProductCreateDto fromObject, Product toObject) {
        toObject.setName(fromObject.getName());
        toObject.setDescription(fromObject.getDescription());
        toObject.setPrice(fromObject.getPrice());
        toObject.setProductType(fromObject.getProductType());

        String imageName = googleCloudService.uploadFile(fromObject.getImage());
        toObject.setImage(imageName);
    }
}
