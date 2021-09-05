package com.mpec.thong_so_ky_thuat.repository;

import com.mpec.thong_so_ky_thuat.entities.ThongSoKiThuat;
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
public interface ThongSoKiThuatRepo extends JpaRepository<ThongSoKiThuat, Integer> {
    @Query("from ThongSoKiThuat th where th.xoa = false ")
    Page<ThongSoKiThuat> findAll(Pageable pageable);

    @Query("from ThongSoKiThuat th where th.id = ?1 and th.xoa = false ")
    Optional<ThongSoKiThuat> findById(Integer id);

    @Query("from ThongSoKiThuat th where th.nhomThongSo.id = ?1 and th.xoa = false ")
    List<ThongSoKiThuat> findThongSoKiThuatByNhomThongSoId(Integer id);

    @Query("from ThongSoKiThuat th where 1=1 and " +
            "(?1 is null or th.tenThongSo like concat('%',?1,'%'))" +
            "and th.xoa = false ")
    Page<ThongSoKiThuat>search(String text, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update ThongSoKiThuat th set th.xoa = true where th.id = ?1")
    Integer delete(int id);

}
