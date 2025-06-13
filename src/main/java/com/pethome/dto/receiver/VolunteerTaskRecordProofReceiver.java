package com.pethome.dto.receiver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ：Star
 * @description ：无描述
 * @date ：2025 6月 13 15:35
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerTaskRecordProofReceiver implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private MultipartFile[] imageArray;
}