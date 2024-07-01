package com.medivision.medivision.search.controller;

import com.medivision.medivision.search.domain.service.SearchService;
import com.medivision.medivision.search.dto.request.SearchRequestDto;
import com.medivision.pacs.entity.VStudyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping()
    public String search(){
        return "search/search";
    }

    @GetMapping("findall")
    @ResponseBody
    public List<VStudyEntity> findAll(){
        return searchService.findAll();
    }

    @GetMapping("detail")
    @ResponseBody
    public List<VStudyEntity> findById(@ModelAttribute SearchRequestDto searchRequestDto){
        List<VStudyEntity> result = new ArrayList<>();
        List<VStudyEntity> temp = new ArrayList<>();

        List<VStudyEntity> findPid = new ArrayList<>();
        List<VStudyEntity> findPName = new ArrayList<>();
        List<VStudyEntity> findReportStatus = new ArrayList<>();
        List<VStudyEntity> findModality = new ArrayList<>();
        List<VStudyEntity> findAll = findAll();

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
                VStudyEntity vStudyEntity = findPName.get(i);

                for(int j=0; j<result.size(); j++){
                    if(vStudyEntity.getStudyKey() == result.get(j).getStudyKey()){
                        temp.add(vStudyEntity);
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
                VStudyEntity vStudyEntity = findReportStatus.get(i);

                for(int j=0; j<result.size(); j++){
                    if(vStudyEntity.getStudyKey() == result.get(j).getStudyKey()){
                        temp.add(vStudyEntity);
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
                VStudyEntity vStudyEntity = findModality.get(i);

                for(int j=0; j<result.size(); j++){
                    if(vStudyEntity.getStudyKey() == result.get(j).getStudyKey()){
                        temp.add(vStudyEntity);
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
                VStudyEntity vStudyEntity = result.get(i);
                int date = searchService.dateformat(vStudyEntity.getStudydate());
                if(date >= startDate && date <= endDate){
                    temp.add(vStudyEntity);
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
