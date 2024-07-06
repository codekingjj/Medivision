package com.medivision.medivision.search.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FileResponse {
    private String fileName;
    private String fileType;
    private String base64Content;
}
