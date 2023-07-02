package com.web.FileTran.vo;


import com.web.FileTran.dao.filesMapper;
import com.web.FileTran.dao.foldersMapper;
import com.web.FileTran.dao.usersMapper;
import com.web.FileTran.pojo.files;
import com.web.FileTran.pojo.folders;
import org.springframework.beans.factory.annotation.Autowired;

public class ShortcutVO {
    @Autowired
    private static usersMapper usersMapper;
    @Autowired
    private static filesMapper filesMapper;
    @Autowired
    private static foldersMapper foldersMapper;
    private int targetId;
    private String targetName;
    private UserVO creator;

    public ShortcutVO(int targetId, String targetName, UserVO creator) {
        this.targetId = targetId;
        this.targetName = targetName;
        this.creator = creator;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public UserVO getCreator() {
        return creator;
    }

    public void setCreator(UserVO creator) {
        this.creator = creator;
    }

    public static ShortcutVO ShortcutVOAutoCreate_File(int Destination)
    {
        files source = filesMapper.getFileInfo(Destination);
        return new ShortcutVO(
                Destination,
                source.getName(),
                usersMapper.getUserInfoById(source.getCreatorId()).convertToUserDTO().convertToUserVO()
        );
    }
    public static ShortcutVO ShortcutVOAutoCreate_Folder(int Destination)
    {
        folders source = foldersMapper.getFolderInfo(Destination);
        return new ShortcutVO(
                Destination,
                source.getName(),
                usersMapper.getUserInfoById(source.getCreatorId()).convertToUserDTO().convertToUserVO()
        );
    }
}
