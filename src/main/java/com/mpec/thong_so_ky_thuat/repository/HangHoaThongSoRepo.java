package com.mpec.thong_so_ky_thuat.repository;

import com.mpec.thong_so_ky_thuat.entities.HangHoaThongSo;
import com.mpec.thong_so_ky_thuat.entities.HangHoaThongSo;
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
public interface HangHoaThongSoRepo extends JpaRepository<HangHoaThongSo, Integer> {

    @Query("from HangHoaThongSo th where th.xoa = false ")
    Page<HangHoaThongSo> findAll(Pageable pageable);

    @Query("from HangHoaThongSo th where th.id = ?1 and th.xoa = false ")
    Optional<HangHoaThongSo> findById(Integer id);

    @Query("from HangHoaThongSo th where th.hangHoa.id = ?1 and th.xoa = false ")
    List<HangHoaThongSo> findByHangHoaId(Integer id);

    @Query("from HangHoaThongSo th where th.thongSoKiThuat.id = ?1 and th.xoa = false ")
    List<HangHoaThongSo> findByThongSoKiThuatId(Integer id);

    //    @Query("from HangHoaThongSo th where 1=1 and " +
//            "(?1 is null or th. like concat('%',?1,'%'))" +
//            "and th.xoa = false ")
//    Page<HangHoaThongSo>search(String text, Pageable pageable);
    @Query("from HangHoaThongSo th where th.thongSoChiTiet.id = ?1 and th.xoa = false ")
    List<HangHoaThongSo> findByThongSoChiTiet(Integer id);

    @Modifying
    @Transactional
    @Query(value = "update HangHoaThongSo th set th.xoa = true where th.id = ?1")
    Integer delete(int id);
}
