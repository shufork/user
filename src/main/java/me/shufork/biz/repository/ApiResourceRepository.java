package me.shufork.biz.repository;

import me.shufork.biz.domain.ApiResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiResourceRepository extends JpaRepository<ApiResource,String> {
}
