package com.novo.personalproject.service;

import com.novo.personalproject.dao.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    @Autowired
    private final ShoppingCartRepository shoppingCartRepository;



}
