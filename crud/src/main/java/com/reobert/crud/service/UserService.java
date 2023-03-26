package com.reobert.crud.service;

import com.reobert.crud.entity.User;
import com.reobert.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService{

    List<User> getAll();

    User getOne(Integer id);

    void save(User user);

    void delete(User user);

 }
