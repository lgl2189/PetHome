package com.pethome.dto.sender;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.pethome.entity.mybatis.FileRecord;
import com.pethome.entity.mybatis.VolunteerTaskRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 6月 10 14:32
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerTaskRecordSender implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonUnwrapped
    private VolunteerTaskRecord volunteerTaskRecord;

    private List<FileRecord> taskProveList;

}