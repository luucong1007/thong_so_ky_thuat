package com.mpec.thong_so_ky_thuat.repository;

import com.mpec.thong_so_ky_thuat.entities.NhomThongSo;
import com.mpec.thong_so_ky_thuat.entities.NhomThongSo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface NhomThongSoRepo extends JpaRepository<NhomThongSo, Integer> {
    @Query("from NhomThongSo th where th.xoa = false ")
    Page<NhomThongSo> findAll(Pageable pageable);

    @Query("from NhomThongSo th where th.id = ?1 and th.xoa = false ")
    Optional<NhomThongSo> findById(Integer id);

    @Query("from NhomThongSo th where 1=1 and " +
            "(?1 is null or th.maNhomThongSo like concat('%',?1,'%'))" +
            "and (?2 is null or th.tenNhomThongSo like concat('%',?2,'%'))"+
            "and th.xoa = false ")
    Page<NhomThongSo>search(String ma, String tenNhomThongSo, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update NhomThongSo th set th.xoa = true where th.id = ?1")
    Integer delete(int id);

}
