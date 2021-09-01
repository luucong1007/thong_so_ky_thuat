package com.mpec.thong_so_ky_thuat.repository;

import com.mpec.thong_so_ky_thuat.entities.HangHoa;
import com.mpec.thong_so_ky_thuat.entities.HangHoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface HangHoaRepo extends JpaRepository<HangHoa, Integer> {
    @Query("from HangHoa th where th.xoa = false ")
    Page<HangHoa> findAll(Pageable pageable);

    @Query("from HangHoa th where th.id = ?1 and th.xoa = false ")
    Optional<HangHoa> findById(Integer id);

    @Query("from HangHoa th where 1=1 and " +
            "(?1 is null or th.ma like concat('%',?1,'%'))" +
            "and (?2 is null or th.tenHangHoa like concat('%',?2,'%'))"+
            "and th.xoa = false ")
    Page<HangHoa>search(String ma, String tenHangHoa, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update HangHoa th set th.xoa = true where th.id = ?1")
    Integer delete(int id);

}
