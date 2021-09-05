package com.mpec.thong_so_ky_thuat.repository;

import com.mpec.thong_so_ky_thuat.entities.ThongSoChiTiet;
import com.mpec.thong_so_ky_thuat.entities.ThongSoChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThongSoChiTietRepo extends JpaRepository<ThongSoChiTiet, Integer> {
    @Query("from ThongSoChiTiet th where th.xoa = false ")
    Page<ThongSoChiTiet> findAll(Pageable pageable);

    @Query("from ThongSoChiTiet th where th.id = ?1 and th.xoa = false ")
    Optional<ThongSoChiTiet> findById(Integer id);

    @Query("from ThongSoChiTiet th where th.thongSoKiThuat.id = ?1 and th.xoa = false ")
    List<ThongSoChiTiet> findThongSoChiTietByThongSoKiThuatId(Integer id);

    @Query("from ThongSoChiTiet th where 1=1 and " +
            "(?1 is null or th.ten like concat('%',?1,'%'))" +
            "and th.xoa = false ")
    Page<ThongSoChiTiet>search(String text, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update ThongSoChiTiet th set th.xoa = true where th.id = ?1")
    Integer delete(int id);
}
