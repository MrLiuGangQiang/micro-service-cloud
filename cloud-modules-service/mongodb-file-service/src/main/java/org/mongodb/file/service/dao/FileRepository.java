package org.mongodb.file.service.dao;

import org.mongodb.file.service.entity.File;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Copyright © 2018 Fist Team. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年12月12日
 * @description: FileRepository
 */
@Repository
public interface FileRepository extends MongoRepository<File, String> {

}
