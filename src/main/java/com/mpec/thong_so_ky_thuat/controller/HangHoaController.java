package com.mpec.thong_so_ky_thuat.controller;


import com.mpec.thong_so_ky_thuat.entities.HangHoa;
import com.mpec.thong_so_ky_thuat.entities.NhomHang;
import com.mpec.thong_so_ky_thuat.entities.dto.HangHoaDTO;
import com.mpec.thong_so_ky_thuat.service.HangHoaService;
import com.mpec.thong_so_ky_thuat.service.NhomHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hang-hoa")
public class HangHoaController {
    @Autowired
    HangHoaService hangHoaService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<HangHoa> hangHoas = hangHoaService.findAll(pageable);
        return ResponseEntity.ok(hangHoas);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
        Optional<HangHoa> hangHoaOptional = hangHoaService.findById(id);
        if (hangHoaOptional.isPresent()) {
            return ResponseEntity.ok(hangHoaOptional.get());
        } else {
            return ResponseEntity.ok("Hàng hoá không tồn tại");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "ma", required = false) String ma,
                                    @RequestParam(name = "tenHangHoa", required = false) String tenHangHoa,
                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<HangHoa> hangHoas = hangHoaService.search(ma, tenHangHoa, pageable);
        return ResponseEntity.ok(hangHoas);
    }

    @PostMapping("/save")
    public HangHoa save(@RequestBody HangHoaDTO hangHoaDTO) {
        Optional<HangHoa> nhomHangOptional = hangHoaService.save(hangHoaDTO);
        if (nhomHangOptional.isPresent()) {
            return nhomHangOptional.get();
        } else {
            return null;
        }
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@RequestBody HangHoaDTO hangHoaDTO) {
        Optional<HangHoa> hangHoaOptional = hangHoaService.update(hangHoaDTO);
        if(hangHoaOptional.isPresent()){
            return ResponseEntity.ok("success");
        }else {
            return ResponseEntity.ok("failed");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        return hangHoaService.delete(id)
                ? ResponseEntity.ok("success") : ResponseEntity.ok("failed");
    }
}
