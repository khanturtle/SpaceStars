package com.spacestar.back.game.repository;

import com.spacestar.back.game.domain.Game;
import com.spacestar.back.game.vo.GameResVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game,Long> {
    @Query(" SELECT new com.spacestar.back.game.vo.GameResVo(0,g.name,g.image) FROM Game g")
    List<GameResVo> findAllGameNames();
}
