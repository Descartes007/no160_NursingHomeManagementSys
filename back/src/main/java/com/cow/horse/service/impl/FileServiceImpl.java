package com.cow.horse.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.StrPool;
import com.cow.horse.common.config.file.FileTempPath;
import com.cow.horse.common.config.file.FileUploadConfigPropertity;
import com.cow.horse.common.constant.YesNoEnum;
import com.cow.horse.dao.mapper.BaseAttachmentMapper;
import com.cow.horse.entity.base.Result;
import com.cow.horse.entity.po.BaseAttachment;
import com.cow.horse.entity.vo.FileInfoVo;
import com.cow.horse.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    private FileTempPath fileTempPath;

    @Resource
    private BaseAttachmentMapper baseAttachmentMapper;

    @Resource
    private FileUploadConfigPropertity fileUploadConfigPropertity;

    @Override
    public Result save(MultipartFile file, String module) {
        long size = file.getSize();
        String originalFilename = file.getOriginalFilename();
        File save = fileTempPath.saveCommFile(file, module);
        String filePath = save.getAbsolutePath();
        String downloadUrl = fileUploadConfigPropertity.getUploadHead() + filePath
                .replace(fileTempPath.getRootPath(), "")
                .replace(StrPool.BACKSLASH, StrPool.SLASH);

        BaseAttachment baseAttachment = new BaseAttachment();
        baseAttachment.setName(save.getName());
        baseAttachment.setRealName(originalFilename);
        baseAttachment.setPath(save.getAbsolutePath());
        baseAttachment.setUrl(downloadUrl);
        baseAttachment.setSuff(FileUtil.getSuffix(save));
        baseAttachment.setSize(size);
        baseAttachment.setDelFlag(YesNoEnum.NO.getCode());
        baseAttachmentMapper.insert(baseAttachment);

        return Result.success(BeanUtil.toBean(baseAttachment, FileInfoVo.class));
    }
}
