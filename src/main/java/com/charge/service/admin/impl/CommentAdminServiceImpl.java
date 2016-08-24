package com.charge.service.admin.impl;

import com.charge.dao.CommentMapper;
import com.charge.service.admin.CommentAdminServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liumw
 * @date 2016/8/24 0024
 */
@Service("commentAdminService")
public class CommentAdminServiceImpl implements CommentAdminServiceI {
    @Autowired
    private CommentMapper commentMapper;


}
