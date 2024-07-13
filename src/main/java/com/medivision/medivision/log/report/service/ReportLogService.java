package com.medivision.medivision.log.report.service;

public interface ReportLogService {

    void reportRead(int reportIndex, String userCode, String ip);
    void reportCreate(int reportIndex);
}
