package com.fivesolutions.safetravel.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.fivesolutions.safetravel.entity.CommentaryEntity;

public interface CommentaryJpaRepository extends PagingAndSortingRepository<CommentaryEntity, Integer> {

	@Query("SELECT c FROM CommentaryEntity c inner join c.product pr WHERE pr.id =:productId AND c.deleted=false AND pr.deleted=false order by 1 desc")
	public List<CommentaryEntity> getCommentaryByProductId(@Param("productId") Integer productId);
	
}
