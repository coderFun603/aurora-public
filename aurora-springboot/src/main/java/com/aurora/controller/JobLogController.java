package com.aurora.controller;

import com.aurora.annotation.OptLog;
import com.aurora.dto.JobLogDTO;
import com.aurora.service.JobLogService;
import com.aurora.vo.JobLogSearchVO;
import com.aurora.vo.PageResult;
import com.aurora.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.aurora.constant.OptTypeConst.DELETE;

@Api(tags = "定时任务日志模块")
@RestController
public class JobLogController {

    @Autowired
    private JobLogService jobLogService;

    @ApiOperation("获取定时任务的日志列表")
    @GetMapping("/admin/jobLogs")
    public Result<PageResult<JobLogDTO>> listJobLogs(JobLogSearchVO jobLogSearchVO) {
        return Result.ok(jobLogService.listJobLogs(jobLogSearchVO));
    }

    @OptLog(optType = DELETE)
    @ApiOperation("删除定时任务的日志")
    @DeleteMapping("/admin/jobLogs")
    public Result<?> deleteJobLogs(@RequestBody List<Integer> ids) {
        jobLogService.deleteJobLogs(ids);
        return Result.ok();
    }

    @OptLog(optType = DELETE)
    @ApiOperation("清除定时任务的日志")
    @DeleteMapping("/admin/jobLogs/clean")
    public Result<?> cleanJobLogs() {
        jobLogService.cleanJobLogs();
        return Result.ok();
    }

    @ApiOperation("获取定时任务日志的所有组名")
    @GetMapping("/admin/jobLogs/jobGroups")
    public Result<?> listJobLogGroups() {
        return Result.ok(jobLogService.listJobLogGroups());
    }
}
