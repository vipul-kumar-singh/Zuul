package com.ZuulGateway;

import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<User, Integer> {
}
