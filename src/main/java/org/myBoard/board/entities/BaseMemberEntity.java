package org.myBoard.board.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter @Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseMemberEntity extends BaseEntitiy{

    @CreatedBy
    @Column(length = 40, updatable = false) // 수정시 X
    private String createdBy; // 생성자

    @LastModifiedBy
    @Column(length = 40, insertable = false) // 생성시 X
    private String modifiedBy; // 수정자
}
