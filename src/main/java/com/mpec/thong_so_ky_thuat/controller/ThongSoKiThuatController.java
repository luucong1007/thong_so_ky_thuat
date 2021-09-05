package com.mpec.thong_so_ky_thuat.controller;

import com.mpec.thong_so_ky_thuat.entities.ThongSoKiThuat;
import com.mpec.thong_so_ky_thuat.service.ThongSoKiThuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/thong-so-ki-thuat")
public class ThongSoKiThuatController {
    @Autowired
    ThongSoKiThuatService thongSoKiThuatService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThongSoKiThuat> thongSoKiThuats = thongSoKiThuatService.findAll(pageable);
        return ResponseEntity.ok(thongSoKiThuats);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
        Optional<ThongSoKiThuat> thongSoKiThuatOptional = thongSoKiThuatService.findById(id);
        if (thongSoKiThuatOptional.isPresent()) {
            return ResponseEntity.ok(thongSoKiThuatOptional.get());
        } else {
            return ResponseEntity.ok("Thong so không tồn tại");
        }
    }

    @GetMapping("/find-by-nhom-thong-so-id/{id}")
    public ResponseEntity<?> findThongSoKiThuatByNhomThongSoId(@PathVariable(name = "id") Integer id) {
        List<ThongSoKiThuat> thongSoKiThuats = thongSoKiThuatService.findThongSoKiThuatByNhomThongSoId(id);
        if (thongSoKiThuats !=null) {
            return ResponseEntity.ok(thongSoKiThuats);
        } else {
            return ResponseEntity.ok("Nhom Thong so id không tồn tại");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "text", required = false) String text,
                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ThongSoKiThuat> thongSoKiThuats = thongSoKiThuatService.search(text, pageable);
        return ResponseEntity.ok(thongSoKiThuats);
    }

    @PostMapping("/save")
    public ThongSoKiThuat save(@RequestBody ThongSoKiThuat thongSoKiThuat,
                               @RequestParam(name = "nhomThongSoId") int nhomThongSoId,
                               @RequestParam(name = "nhomHangId") int nhomHangId) {
        Optional<ThongSoKiThuat> thongSoKiThuatOptional = thongSoKiThuatService.save(thongSoKiThuat, nhomHangId, nhomThongSoId);
        if (thongSoKiThuatOptional.isPresent()) {
            return thongSoKiThuatOptional.get();
        } else {
            return null;
        }
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@RequestBody ThongSoKiThuat nhomThongSo,
                             @RequestParam(name = "nhomThongSoId") int nhomThongSoId,
                             @RequestParam(name = "nhomHangId") int nhomHangId) {
        Optional<ThongSoKiThuat> thongSoKiThuatOptional = thongSoKiThuatService.update(nhomThongSo, nhomHangId, nhomThongSoId);
        if (thongSoKiThuatOptional.isPresent()) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.ok("failed");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        return thongSoKiThuatService.delete(id)
                ? ResponseEntity.ok("success") : ResponseEntity.ok("failed");
    }
}
