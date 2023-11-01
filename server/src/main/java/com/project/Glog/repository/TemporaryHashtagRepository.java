package com.project.Glog.repository;

import com.project.Glog.domain.Temporary;
import com.project.Glog.domain.TemporaryHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemporaryHashtagRepository extends JpaRepository<TemporaryHashtag, Long> {
    List<TemporaryHashtag> findTemporaryHashtagsByTemporary(Temporary temporary);

}
