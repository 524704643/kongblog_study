package com.website.hoho.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Archives implements Serializable {
    private Integer archivesId; //id
    private String archivesTitle; //标题
    private String archivesContent; //内容
    private Date createTime; //创建时间
    private String archivesType; //文章类别

//    public Archives(Integer archivesId, String archivesTitle, String archivesContent,String archivesType) {
//        this.archivesId = archivesId;
//        this.archivesTitle = archivesTitle;
//        this.archivesContent = archivesContent;
//        //系统自动创建时间
//        this.createTime = createTime ;
//        this.archivesType = archivesType;
//    }
}
