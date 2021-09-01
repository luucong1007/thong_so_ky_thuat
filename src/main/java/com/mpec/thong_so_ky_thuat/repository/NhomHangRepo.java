package com.mpec.thong_so_ky_thuat.repository;

import com.mpec.thong_so_ky_thuat.entities.NhomHang;
import com.mpec.thong_so_ky_thuat.entities.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface NhomHangRepo extends JpaRepository<NhomHang, Integer> {
    @Query("from NhomHang th where th.xoa = false ")
    Page<NhomHang> findAll(Pageable pageable);

    @Query("from NhomHang th where th.id = ?1 and th.xoa = false ")
    Optional<NhomHang> findById(Integer id);

    @Query("from NhomHang th where 1=1 and " +
            "(?1 is null or th.maNhomHang like concat('%',?1,'%'))" +
            "and (?2 is null or th.tenNhomHang like concat('%',?2,'%'))"+
            "and th.xoa = false ")
    Page<NhomHang>search(String maNhomHang, String tenNhomHang, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update NhomHang th set th.xoa = true where th.id = ?1")
    Integer delete(int id);

}
