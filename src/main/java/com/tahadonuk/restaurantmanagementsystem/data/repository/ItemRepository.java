package com.tahadonuk.restaurantmanagementsystem.data.repository;

import com.tahadonuk.restaurantmanagementsystem.data.entity.item.Item;
import com.tahadonuk.restaurantmanagementsystem.data.entity.item.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByItemType(ItemType type);
    boolean existsByItemType(ItemType type);

    List<Item> findAllByNameContains(String  name);
    boolean existsByNameContains(String name);
}
