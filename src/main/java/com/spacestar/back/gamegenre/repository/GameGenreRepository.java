package com.spacestar.back.gamegenre.repository;

import com.spacestar.back.gamegenre.domain.GameGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameGenreRepository extends JpaRepository<GameGenre,Long>{
}
