package com.mpec.thong_so_ky_thuat.controller;

import com.mpec.thong_so_ky_thuat.entities.HangHoaThongSo;
import com.mpec.thong_so_ky_thuat.service.HangHoaThongSoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hang-hoa-thong-so")
public class HangHoaThongSoController {
    @Autowired
    HangHoaThongSoService hangHoaThongSoService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<HangHoaThongSo> hangHoaThongSos = hangHoaThongSoService.findAll(pageable);
        return ResponseEntity.ok(hangHoaThongSos);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
        Optional<HangHoaThongSo> hangHoaThongSoOptional = hangHoaThongSoService.findById(id);
        if (hangHoaThongSoOptional.isPresent()) {
            return ResponseEntity.ok(hangHoaThongSoOptional.get());
        } else {
            return ResponseEntity.ok("Thong so chi tiet không tồn tại");
        }
    }

    @GetMapping("/find-by-hang-hoa-id/{id}")
    public ResponseEntity<?> findByHangHoaId(@PathVariable(name = "id") Integer id) {
        List<HangHoaThongSo> hangHoaThongSos = hangHoaThongSoService.findByHangHoaId(id);
        if (hangHoaThongSos !=null) {
            return ResponseEntity.ok(hangHoaThongSos);
        } else {
            return ResponseEntity.ok("Thong so ki thuat id không tồn tại");
        }
    }

    @GetMapping("/find-by-thong-so-ki-thuat-id/{id}")
    public ResponseEntity<?> findByThongSoKiThuatId(@PathVariable(name = "id") Integer id) {
        List<HangHoaThongSo> hangHoaThongSos = hangHoaThongSoService.findByThongSoKiThuatId(id);
        if (hangHoaThongSos !=null) {
            return ResponseEntity.ok(hangHoaThongSos);
        } else {
            return ResponseEntity.ok("Thong so ki thuat id không tồn tại");
        }
    }



    @GetMapping("/find-by-thong-so-chi-tiet-id/{id}")
    public ResponseEntity<?> findByThongSoChiTietId(@PathVariable(name = "id") Integer id) {
        List<HangHoaThongSo> hangHoaThongSos = hangHoaThongSoService.findByThongSoChiTietId(id);
        if (hangHoaThongSos !=null) {
            return ResponseEntity.ok(hangHoaThongSos);
        } else {
            return ResponseEntity.ok("Thong so chi tiet id không tồn tại");
        }
    }

    @PostMapping("/save")
    public HangHoaThongSo save(@RequestBody HangHoaThongSo hangHoaThongSo,
                               @RequestParam(name = "hangHoaId") int hangHoaId,
                               @RequestParam(name = "thongSoKiThuatId") int thongSoKiThuatId,
                               @RequestParam(name = "thongSoChiTietId") int thongSoChiTietID) {
        Optional<HangHoaThongSo> hangHoaThongSoOptional = hangHoaThongSoService.save(hangHoaThongSo, hangHoaId, thongSoKiThuatId, thongSoChiTietID);
        if (hangHoaThongSoOptional.isPresent()) {
            return hangHoaThongSoOptional.get();
        } else {
            return null;
        }
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@RequestBody HangHoaThongSo hangHoaThongSo,
                             @RequestParam(name = "hangHoaId") int hangHoaId,
                             @RequestParam(name = "thongSoKiThuatId") int thongSoKiThuatId,
                             @RequestParam(name = "thongSoChiTietId") int thongSoChiTietID) {
        Optional<HangHoaThongSo> hangHoaThongSoOptional = hangHoaThongSoService.update(hangHoaThongSo,hangHoaId,thongSoKiThuatId,thongSoChiTietID);
        if (hangHoaThongSoOptional.isPresent()) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.ok("failed");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        return hangHoaThongSoService.delete(id)
                ? ResponseEntity.ok("success") : ResponseEntity.ok("failed");
    }
}
