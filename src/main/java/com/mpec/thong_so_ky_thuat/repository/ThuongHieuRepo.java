package com.mpec.thong_so_ky_thuat.repository;

import com.mpec.thong_so_ky_thuat.entities.ThuongHieu;
import org.hibernate.engine.jdbc.BinaryStream;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ThuongHieuRepo extends JpaRepository<ThuongHieu, Integer> {

    @Query("from ThuongHieu th where th.xoa = 0")
    Page<ThuongHieu>findAll(Pageable pageable);

    @Query("from ThuongHieu th where th.id = ?1 and th.xoa = 0")
    Optional<ThuongHieu> findById(Integer id);

    @Query("from ThuongHieu th where 1= 1 and" +
            "(?1 is null or th.tenThuongHieu like concat('%',?1,'%'))" +
            "and th.xoa = 0")
    Page<ThuongHieu>search(String text, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update ThuongHieu th  set th.tenThuongHieu = ?2 where  th.id = ?1")
    Integer updateTenThuongHieu(Integer id, String text);

    @Modifying
    @Transactional
    @Query(value = "update ThuongHieu th set th.xoa = 1 where th.id = ?1")
    Integer delete(int id);
}
