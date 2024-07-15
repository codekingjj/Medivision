package com.medivision.medivision.main.controller;

import com.medivision.medivision.main.domain.MainService;
import com.medivision.medivision.search.domain.service.SearchService;
import com.medivision.medivision.search.dto.request.SearchRequestDto;
import com.medivision.pacs.entity.StudyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    private final MainService mainService;
    private final SearchService searchService;

    @GetMapping()
    public String main() {
        return "main/main";
    }

    @GetMapping("setting")
    @ResponseBody
    public List<StudyEntity> setting() {
        return mainService.findAll();
    }

    @GetMapping("date")
    @ResponseBody
    public List<StudyEntity> dateSearch(String date){
        LocalDateTime now = LocalDateTime.now();
        List<StudyEntity> list = mainService.findAll();
        List<StudyEntity> result = new ArrayList<>();
        if(date.equals("three-days")){
            String threeDaysAgo = LocalDate.now().minusDays(3).toString().replace("-", "");
            for(StudyEntity StudyEntity : list){
                int studydate = Integer.parseInt(StudyEntity.getStudydate());
                if(studydate >= Integer.parseInt(threeDaysAgo)){
                    result.add(StudyEntity);
                }
            }
        }else if (date.equals("week")) {
            String week = LocalDate.now().minusDays(7).toString().replace("-", "");
            for(StudyEntity StudyEntity : list){
                int studydate = Integer.parseInt(StudyEntity.getStudydate());
                if(studydate >= Integer.parseInt(week)){
                    result.add(StudyEntity);
                }
            }
        }else if (date.equals("all")) {
            result = list;
        }

        return result;
    }


    @GetMapping("search")
    @ResponseBody
    public List<StudyEntity> search(@ModelAttribute SearchRequestDto searchRequestDto) {
        List<StudyEntity> result = new ArrayList<>();
        List<StudyEntity> temp = new ArrayList<>();

        List<StudyEntity> findPid = new ArrayList<>();
        List<StudyEntity> findPName = new ArrayList<>();
        List<StudyEntity> findReportStatus = new ArrayList<>();

        boolean pidFlag = false;
        boolean pNameFlag = false;
        boolean reportStatusFlag = false;
        boolean start = false;

        String pid = searchRequestDto.getPid();
        String pname = searchRequestDto.getPname();
        int reportstatus = searchRequestDto.getReportstatus();

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
