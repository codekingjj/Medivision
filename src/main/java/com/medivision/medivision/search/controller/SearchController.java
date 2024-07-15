package com.medivision.medivision.search.controller;

import com.medivision.medivision.log.study.domain.service.StudyLogService;
import com.medivision.medivision.search.domain.service.SearchService;
import com.medivision.medivision.search.dto.request.SearchRequestDto;
import com.medivision.medivision.search.dto.response.FileResponse;
import com.medivision.pacs.entity.StudyEntity;
import com.medivision.pacs.entity.VSeriesEntity;
import com.medivision.pacs.service.VSeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;
    private final VSeriesService vSeriesService;

    private final StudyLogService studyLogService;

    @GetMapping()
    public String search(){
        return "search/search";
    }

    @GetMapping("series")
    @ResponseBody
    public List<VSeriesEntity> getSeries(int studyKey){
        List<VSeriesEntity> list = vSeriesService.findStudyKey(studyKey);
        List<File> fileList = new ArrayList<>();
        String driver = "Z:\\";
        for(int i=0; i<list.size(); i++){
            VSeriesEntity series = list.get(i);
            String path = series.getPath();
            String fileName = series.getFName();
            String realPath = driver + path + fileName;
            File file = new File(realPath);
            fileList.add(file);
        }


        return list;
    }

    @GetMapping("file")
    @ResponseBody
    public List<FileResponse> getFiles(int studyKey) {
        System.out.println(studyKey);
        List<VSeriesEntity> list = vSeriesService.findStudyKey(studyKey);
        List<FileResponse> fileList = new ArrayList<>();
        String driver = "Z:\\";
        for (int i = 0; i < list.size(); i++) {
            VSeriesEntity series = list.get(i);
            String path = series.getPath();
            String fileName = series.getFName();
            String realPath = driver + path + fileName;
            File file = new File(realPath);

            // 파일을 Base64로 인코딩
            try {
                FileResponse fileResponse = new FileResponse();
                fileResponse.setFileName(fileName);
                fileResponse.setFileType(Files.probeContentType(file.toPath()));
                fileResponse.setBase64Content(encodeFileToBase64(file));
                fileList.add(fileResponse);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileList;
    }

    private String encodeFileToBase64(File file) throws IOException {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }


    @GetMapping("findall")
    @ResponseBody
    public List<StudyEntity> findAll(){
        return searchService.findAll();
    }

    @GetMapping("detail")
    @ResponseBody
    public List<StudyEntity> findById(@ModelAttribute SearchRequestDto searchRequestDto, @AuthenticationPrincipal String userCode, HttpServletRequest request){

        // study 열람시 로그 찍기 로직
        String ip = request.getRemoteAddr();
        int studyKey = searchRequestDto.getStudyKey();
//        studyLogService.saveStudyLog(userCode,studyKey,ip);

        List<StudyEntity> result = new ArrayList<>();
        List<StudyEntity> temp = new ArrayList<>();

        List<StudyEntity> findPid = new ArrayList<>();
        List<StudyEntity> findPName = new ArrayList<>();
        List<StudyEntity> findReportStatus = new ArrayList<>();
        List<StudyEntity> findModality = new ArrayList<>();
        List<StudyEntity> findAll = findAll();

        boolean pidFlag = false;
        boolean pNameFlag = false;
        boolean reportStatusFlag = false;
        boolean modalityFlag = false;
        boolean dateFlag = false;
        boolean start = false;

        String pid = searchRequestDto.getPid();
        String pname = searchRequestDto.getPname();
        int reportstatus = searchRequestDto.getReportstatus();
        String modality = searchRequestDto.getModality();
        int startDate = searchService.dateformat(searchRequestDto.getStartDate());
        int endDate = searchService.dateformat(searchRequestDto.getEndDate());

        System.out.println(startDate + " " + endDate);

        if(!pid.isEmpty()){
            findPid = searchService.findByPidLike(pid);
            pidFlag = true;
        }

        if(!pname.isEmpty()){
            findPName = searchService.findByPnameLike(pname);
            pNameFlag = true;
        }

        if(reportstatus != -1){
            findReportStatus = searchService.findByReportstatus(reportstatus);
            reportStatusFlag = true;
        }

        if(!modality.isEmpty()){
            findModality = searchService.findByModality(modality);
            modalityFlag = true;
        }
        if(startDate <= endDate){
            dateFlag = true;
        }

        if(pidFlag){
            result.addAll(findPid);
            start = true;
        }


        if(pNameFlag && !start){
            result.addAll(findPName);
            start = true;
        } else if (pNameFlag && start) {
            for(int i=0; i<findPName.size(); i++){
                StudyEntity StudyEntity = findPName.get(i);

                for(int j=0; j<result.size(); j++){
                    if(StudyEntity.getStudykey() == result.get(j).getStudykey()){
                        temp.add(StudyEntity);
                    }
                }
            }
            result.clear();
            result.addAll(temp);
            temp.clear();
        }

        if(reportStatusFlag && !start){
            result.addAll(findReportStatus);
            start = true;
        } else if (reportStatusFlag && start) {
            for(int i=0; i<findReportStatus.size(); i++){
                StudyEntity StudyEntity = findReportStatus.get(i);

                for(int j=0; j<result.size(); j++){
                    if(StudyEntity.getStudykey() == result.get(j).getStudykey()){
                        temp.add(StudyEntity);
                    }
                }
            }
            result.clear();
            result.addAll(temp);
            temp.clear();
        }

        if(modalityFlag && !start){
            result.addAll(findModality);
            start = true;
        } else if (modalityFlag && start) {
            for(int i=0; i<findModality.size(); i++){
                StudyEntity StudyEntity = findModality.get(i);

                for(int j=0; j<result.size(); j++){
                    if(StudyEntity.getStudykey() == result.get(j).getStudykey()){
                        temp.add(StudyEntity);
                    }
                }
            }
            result.clear();
            result.addAll(temp);
            temp.clear();
        }

        if(dateFlag && !start){
            start = true;
            result = searchService.findDateSearch(startDate,endDate);

        }else if (dateFlag && start) {
            for(int i=0; i<result.size(); i++){
                StudyEntity StudyEntity = result.get(i);
                int date = searchService.dateformat(StudyEntity.getStudydate());
                if(date >= startDate && date <= endDate){
                    temp.add(StudyEntity);
                }
            }
            result.clear();
            result.addAll(temp);
            temp.clear();
        }
        
        
        // 날짜기준 내림차순
        if(result.size() > 0){
            result.sort((v1, v2) -> {
                int date1 = searchService.dateformat(v1.getStudydate());
                int date2 = searchService.dateformat(v2.getStudydate());
                return Integer.compare(date2, date1); // descending order
            });
        }

        return  result;

    }



}
