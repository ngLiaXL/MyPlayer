
package com.ngliaxl.myplayer.persistence.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ngliaxl.myplayer.persistence.tables.MediaT;

import java.util.List;

@Dao
public interface MediaDao extends BaseDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(List<MediaT> medias);

    @Query("DELETE FROM media")
    int deleteAll();

    @Query("DELETE FROM media where id=:id")
    int delete(int id);

    @Query("SELECT * FROM media")
    List<MediaT> getMedias();

    @Query("SELECT * FROM media WHERE status IN (:status)")
    List<MediaT> getMedias(List<Integer> status);

    @Query("SELECT * FROM media WHERE status IN (:status) AND category=:category")
    List<MediaT> getMedias(List<Integer> status, @MediaT.MediaCategory.CATEGORY int category);

    @Query("SELECT * FROM media  GROUP BY type ORDER BY id")
    List<MediaT> getGroupMedias();

    @Query("UPDATE media SET status=:status WHERE id=:id")
    int update(@MediaT.MediaStatus.Status int status, int id);


}
