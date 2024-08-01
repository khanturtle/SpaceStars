package com.spacestar.back.rate.repository.level;

import com.spacestar.back.rate.domain.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level,Long> ,LevelRepositoryCustom{
}
