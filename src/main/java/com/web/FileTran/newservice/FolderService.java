package com.web.FileTran.newservice;

import com.web.FileTran.newvo.FolderContentVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
@Service
public class FolderService {
    // Get folder content by ID, page, and page size
    public FolderContentVO getFolderContent(long folderId, int page, int pageSize, HttpSession session) {
        // Implement the logic to retrieve folder content by ID, page, and page size
        // ...
        FolderContentVO folderContent = new FolderContentVO();
        // Set the properties of the folderContent object based on the retrieved information
        // ...
        return folderContent;
    }
}
