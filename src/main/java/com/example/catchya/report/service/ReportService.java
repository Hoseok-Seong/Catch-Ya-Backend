package com.example.catchya.report.service;

import com.example.catchya.global.security.MyUserDetails;
import com.example.catchya.report.dto.ReportFindReq;
import com.example.catchya.report.dto.ReportFindResp;
import com.example.catchya.report.dto.ReportInsertReq;
import com.example.catchya.report.dto.ReportInsertResp;
import com.example.catchya.report.entity.Report;
import com.example.catchya.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    @Transactional
    public ResponseEntity<?> insertReport(MyUserDetails myUserDetails,
                                          ReportInsertReq reportInsertReq) {
        if(reportInsertReq.username().equals(myUserDetails.getUsername())) {
            return ResponseEntity.badRequest().body("유저 이름이 일치하지 않습니다");
        }

        Report report = reportRepository.save(reportInsertReq.toEntity());

        return ResponseEntity.ok().body(new ReportInsertResp(report));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getReports(MyUserDetails myUserDetails,
                                        ReportFindReq reportFindReq) {
        if(reportFindReq.username().equals(myUserDetails.getUsername())) {
            return ResponseEntity.badRequest().body("유저 이름이 일치하지 않습니다");
        }

        List<Report> report = reportRepository.findByUsername(reportFindReq.username());

        return ResponseEntity.ok().body(new ReportFindResp(report));
    }
}
