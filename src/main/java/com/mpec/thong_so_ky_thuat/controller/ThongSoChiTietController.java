package com.mpec.thong_so_ky_thuat.controller;

import com.mpec.thong_so_ky_thuat.entities.ThongSoChiTiet;
import com.mpec.thong_so_ky_thuat.service.ThongSoChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/thong-so-chi-tiet")
public class ThongSoChiTietController {
    @Autowired
    ThongSoChiTietService thongSoChiTietService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThongSoChiTiet> thongSoChiTiets = thongSoChiTietService.findAll(pageable);
        return ResponseEntity.ok(thongSoChiTiets);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
        Optional<ThongSoChiTiet> thongSoChiTietOptional = thongSoChiTietService.findById(id);
        if (thongSoChiTietOptional.isPresent()) {
            return ResponseEntity.ok(thongSoChiTietOptional.get());
        } else {
            return ResponseEntity.ok("Thong so chi tiet không tồn tại");
        }
    }

    @GetMapping("/find-by-thong-so-ki-thuat-id/{id}")
    public ResponseEntity<?> findThongSoKiThuatByNhomThongSoId(@PathVariable(name = "id") Integer id) {
        List<ThongSoChiTiet> thongSoChiTiets = thongSoChiTietService.findThongSoChiTietByThongSoKiThuatId(id);
        if (thongSoChiTiets !=null) {
            return ResponseEntity.ok(thongSoChiTiets);
        } else {
            return ResponseEntity.ok("Thong so ki thuat id không tồn tại");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "text", required = false) String text,
                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThongSoChiTiet> thongSoChiTiets = thongSoChiTietService.search(text, pageable);
        return ResponseEntity.ok(thongSoChiTiets);
    }

    @PostMapping("/save")
    public ThongSoChiTiet save(@RequestBody ThongSoChiTiet thongSoChiTiet,
                               @RequestParam(name = "thongSoKiThuatId") int thongSoKiThuatId) {
        Optional<ThongSoChiTiet> thongSoChiTietOptional = thongSoChiTietService.save(thongSoChiTiet, thongSoKiThuatId);
        if (thongSoChiTietOptional.isPresent()) {
            return thongSoChiTietOptional.get();
        } else {
            return null;
        }
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@RequestBody ThongSoChiTiet nhomThongSo,
                             @RequestParam(name = "thongSoKiThuatId") int thongSoKiThuatId) {
        Optional<ThongSoChiTiet> thongSoChiTietOptional = thongSoChiTietService.update(nhomThongSo, thongSoKiThuatId);
        if (thongSoChiTietOptional.isPresent()) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.ok("failed");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        return thongSoChiTietService.delete(id)
                ? ResponseEntity.ok("success") : ResponseEntity.ok("failed");
    }
}
