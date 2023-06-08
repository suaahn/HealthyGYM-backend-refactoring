package com.healthy.gym.dao;

import com.healthy.gym.dto.InbodyDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InbodyDao {

    List<InbodyDto> getInbodyList(int memberseq);
    int insertInbody(InbodyDto dto);
    int saveInbody(int inbodyseq);
    int deleteInbody(int inbodyseq);
}
