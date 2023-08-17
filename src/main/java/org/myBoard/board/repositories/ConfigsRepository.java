package org.myBoard.board.repositories;

import org.myBoard.board.entities.Configs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigsRepository extends JpaRepository<Configs, String> {

}
