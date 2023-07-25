package com.example.catchya.report.repository;

import com.example.catchya.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findReportsByUserId(Long userId);
}
